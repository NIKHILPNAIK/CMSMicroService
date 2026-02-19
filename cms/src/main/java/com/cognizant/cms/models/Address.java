package com.cognizant.cms.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Column(name = "house_no", length = 50, nullable = false)
    private String houseNo;

    @Column(name = "street", length = 100, nullable = false)
    private String street;

    @Column(name = "landmark", length = 100, nullable = false)
    private String landmark;

    @Column(name = "city", length = 100, nullable = false)
    private String city;

    @Column(name = "state", length = 100, nullable = false)
    private String state;

    // Per your spec: max 250 chars
    @Column(name = "pin", length = 250, nullable = false)
    private String pin;
    
    
}