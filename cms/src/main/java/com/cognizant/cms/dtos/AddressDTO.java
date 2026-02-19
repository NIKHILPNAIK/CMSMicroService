package com.cognizant.cms.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {
    @NotBlank @Size(max = 50)
    private String houseNo;

    @NotBlank @Size(max = 100)
    private String street;

    @NotBlank @Size(max = 100)
    private String landmark;
    

    @NotBlank @Size(max = 100)
    private String city;

    @NotBlank @Size(max = 100)
    private String state;

    @NotBlank @Size(max = 250)
    private String pin;
}