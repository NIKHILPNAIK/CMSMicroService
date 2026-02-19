package com.cognizant.cms.controllers;

import com.cognizant.cms.dtos.CustomerRequest;
import com.cognizant.cms.dtos.CustomerResponse;
import com.cognizant.cms.dtos.GenericMessage;
import com.cognizant.cms.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/cms")
public class CustomerController {
	
	@Autowired
    private CustomerService service;

    // Create: POST /cms/save – 201
    @PreAuthorize("hasAnyAuthority('SCOPE_developer')")
    @PostMapping("/save")
    public ResponseEntity<GenericMessage<CustomerResponse>> create(@Valid @RequestBody CustomerRequest request) {
        CustomerResponse res = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new GenericMessage<>(res));
    }

    // Read: GET /cms/{id} – 200
    @PreAuthorize("hasAnyAuthority('SCOPE_tester','SCOPE_developer')")
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<String>> getById(@PathVariable Long id) {
         return service.getById(id) .thenApply(result->ResponseEntity.status(HttpStatus.OK)
                .body(result.toString()))
        .exceptionally(ex-> {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        });
    }
    
    @GetMapping("/")
    public List<CustomerResponse> getAll(CustomerRequest request){
    	return service.getAll(request);
    }

    // Update: PUT /cms/save/{id} – 201
    @PreAuthorize("hasAnyAuthority('SCOPE_developer')")
    @PutMapping("/save/{id}")
    public ResponseEntity<GenericMessage<CustomerResponse>> update(@PathVariable Long id,
                                                                   @Valid @RequestBody CustomerRequest request) {
        CustomerResponse res = service.update(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new GenericMessage<>(res));
    }

    // Delete: DELETE /cms/delete/{id} – 202 (or 404)
    @PreAuthorize("hasAnyAuthority('SCOPE_developer')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericMessage<String>> delete(@PathVariable Long id) {
        boolean ok = service.delete(id);
        if (ok) {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new GenericMessage<>("Customer deleted successfully with id: " + id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new GenericMessage<>("Customer not found with id: " + id));
        }
    }
}