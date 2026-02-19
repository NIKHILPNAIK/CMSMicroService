package com.cognizant.addrval.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "address_validation_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressValidationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="address_type", length = 50, nullable = false)
    private String addressType;

    @Column(name="city", length = 100, nullable = false)
    private String city;

    @Column(name="state", length = 100, nullable = false)
    private String state;

    @Column(name="pin", length = 250, nullable = false)
    private String pin;

    @Column(name="is_valid", nullable = false)
    private boolean isValid;

    @Column(name="validation_timestamp", nullable = false)
    private LocalDateTime validationTimestamp;
}