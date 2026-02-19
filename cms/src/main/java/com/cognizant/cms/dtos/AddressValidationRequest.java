package com.cognizant.cms.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressValidationRequest {
    private String addressType; // "permanent" | "communication"
    private String city;
    private String state;
    private String pin;
}