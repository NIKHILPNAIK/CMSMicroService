package com.cognizant.addrval.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressValidationRequest {

    @NotBlank
    private String addressType; // "permanent" | "communication"

    @NotBlank @Size(max = 100)
    private String city;

    @NotBlank @Size(max = 100)
    private String state;

    
    @NotBlank @Size(max = 250)
    private String pin;
}