package com.cognizant.audit.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLogRequest {

    private Long customerId; // can be null for non-customer events

    @NotBlank
    @Size(max = 50)
    private String action;      // CREATE / UPDATE / DELETE

    @NotBlank
    @Size(max = 100)
    private String performedBy; // user/service

    @Size(max = 4000)
    private String details;     // text or JSON

    // Optional; if null, server sets now()
    private LocalDateTime timestamp;
}