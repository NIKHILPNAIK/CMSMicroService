package com.cognizant.cms.services;

import com.cognizant.cms.dtos.*;
import com.cognizant.cms.exceptions.CustomerNotFoundException;
import com.cognizant.cms.mappers.CustomerMapper;
import com.cognizant.cms.models.Customer;
import com.cognizant.cms.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;
    private final RestClient restClient; // provided by RestClientConfig

    @Value("${audit.base-url:http://localhost:7082}")
    private String auditBaseUrl;

    @Value("${address.validation.base-url:http://localhost:7083}")
    private String addrValBaseUrl;

    @Override
    @Transactional
    public CustomerResponse create(CustomerRequest request) {
        // 1) Validate both addresses (will throw if invalid)
        validateAddresses(request);

        // 2) Save
        Customer entity = mapper.toEntity(request);
        entity = repository.save(entity);

        // 3) Audit
        audit(entity.getId(), "CREATE", "Customer created");

        // 4) Response
        return mapper.toResponse(entity);
    }
    
    @Override
    public List<CustomerResponse> getAll(CustomerRequest request) {
    	
    	return mapper.toResponses(repository.findAll());
    	
    }

    @Override
    public CompletableFuture<CustomerResponse> getById(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            Customer entity = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
            return mapper.toResponse(entity);
        });
    }
    @Override
    @Transactional
    public CustomerResponse update(Long id, CustomerRequest request) {
        // 1) Validate first
        validateAddresses(request);

        // 2) Load & update
        Customer entity = repository.findById(id)
            .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        mapper.updateEntityFromRequest(request, entity);
        entity = repository.save(entity);

        // 3) Audit
        audit(id, "UPDATE", "Customer updated");

        // 4) Response
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            throw new CustomerNotFoundException("Customer not found to delete with id: " + id);
        }
        repository.deleteById(id);

        // Audit best-effort
        audit(id, "DELETE", "Customer deleted");
        return true;
    }

    
    // Address Validation (throws if either is invalid)
   
    private void validateAddresses(CustomerRequest request) {

        // permanent
        var p = request.getPermanentAddress();
        AddressValidationRequest pReq = AddressValidationRequest.builder()
                .addressType("permanent")
                .city(p.getCity())
                .state(p.getState())
                .pin(p.getPin())
                .build();

        AddressValidationResponse pRes = restClient
                .post()
                .uri(addrValBaseUrl + "/validation/v1.0/address")
                .body(pReq)
                .retrieve()
                .body(AddressValidationResponse.class);
        
        
        // communication
        var c = request.getCommunicationAddress();
        AddressValidationRequest cReq = AddressValidationRequest.builder()
                .addressType("communication")
                .city(c.getCity())
                .state(c.getState())
                .pin(c.getPin())
                .build();

        AddressValidationResponse cRes = restClient
                .post()
                .uri(addrValBaseUrl + "/validation/v1.0/address")
                .body(cReq)
                .retrieve()
                .body(AddressValidationResponse.class);

        
        if (Boolean.FALSE.equals(pRes.getIsValid()) || Boolean.FALSE.equals(cRes.getIsValid())) {
            String msg = "Address validation failed. "
                    + "[Permanent: " + (pRes != null ? pRes.getMessage() : "no response")
                    + "], [Communication: " + (cRes != null ? cRes.getMessage() : "no response") + "]";
            throw new IllegalArgumentException(msg);
        }
    }

    
    // Audit (best-effort; failure will not break main flow)
  
    private void audit(Long customerId, String action, String details) {
        try {
            AuditLogRequest req = AuditLogRequest.builder()
                    .customerId(customerId)
                    .action(action)
                    .performedBy(currentActor()) // or "cms-service"
                    .details(details)
                    .timestamp(LocalDateTime.now())
                    .build();

            restClient
                .post()
                .uri(auditBaseUrl + "/audit/log")
                .body(req)
                .retrieve()
                .toBodilessEntity();

            log.debug("Audit sent: {} - {}", action, customerId);
        } catch (Exception ex) {
            log.warn("Audit logging failed for customerId={} action={} cause={}",
                    customerId, action, ex.getMessage());
        }
    }

    private String currentActor() {
        var ctx = org.springframework.security.core.context.SecurityContextHolder.getContext();
        var auth = (ctx != null) ? ctx.getAuthentication() : null;
        return (auth != null ? auth.getName() : "cms-service");
    }
}