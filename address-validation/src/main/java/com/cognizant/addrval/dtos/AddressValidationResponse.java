package com.cognizant.addrval.dtos;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressValidationResponse {
    private String addressType;
    private String city;
    private String state;
    private String pin;
    private Boolean isValid;
    private LocalDateTime validationTimestamp;
}