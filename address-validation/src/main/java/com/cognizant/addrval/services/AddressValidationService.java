package com.cognizant.addrval.services;

import com.cognizant.addrval.dtos.AddressValidationRequest;
import com.cognizant.addrval.dtos.AddressValidationResponse;
import com.cognizant.addrval.models.AddressValidationLog;
import com.cognizant.addrval.repositories.AddressValidationLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressValidationService {

    private final AddressValidationLogRepository logRepo;

    @Value("${validation.allowed-states}")
    private String statesCsv;           // inject as a field

    private Set<String> allowedStates;  // computed after construction

    @PostConstruct
    void initAllowedStates() {
        this.allowedStates = Arrays.stream(statesCsv.split(","))
                .map(s -> s.trim().toLowerCase())
                .filter(s -> !s.isBlank())
                .collect(Collectors.toUnmodifiableSet());
    }

    public AddressValidationResponse validate(AddressValidationRequest req) {
        String addressType = req.getAddressType().trim();
        String city        = req.getCity().trim();
        String state       = req.getState().trim();
        String pin         = req.getPin().trim();

        boolean valid = allowedStates.contains(state.toLowerCase());
        LocalDateTime now = LocalDateTime.now();

        // persist every attempt
        AddressValidationLog log = AddressValidationLog.builder()
                .addressType(addressType)
                .city(city)
                .state(state)
                .pin(pin)
                .isValid(valid)
                .validationTimestamp(now)
                .build();
        logRepo.save(log);

        // exact response format you wanted
        return AddressValidationResponse.builder()
                .addressType(addressType)
                .city(city)
                .state(state)
                .pin(pin)
                .isValid(valid)
                .validationTimestamp(now)
                .build();
    }
}