package com.cognizant.addrval.controllers;

import com.cognizant.addrval.dtos.AddressValidationRequest;
import com.cognizant.addrval.dtos.AddressValidationResponse;
import com.cognizant.addrval.services.AddressValidationService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validation")
public class AddressValidationController {

	@Autowired
    private AddressValidationService service;

    @PostMapping("/v1.0/address")
    public ResponseEntity<AddressValidationResponse> validate(@Valid @RequestBody AddressValidationRequest request) {
        AddressValidationResponse result = service.validate(request);

        
        //  - Always 200 OK, return isValid=true/false
        //  - Or return 422 when invalid
        // return result.getIsValid()
        //         ? ResponseEntity.ok(result)
        //         : ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(result);

        return ResponseEntity.ok(result);
    }
}