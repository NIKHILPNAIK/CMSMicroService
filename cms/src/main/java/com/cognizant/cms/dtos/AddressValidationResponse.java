package com.cognizant.cms.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressValidationResponse {
    private Boolean isValid;
    private String message;
}