package com.cognizant.cms.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLogRequest {

    private Long customerId;
    private String action;        // CREATE / UPDATE / DELETE
    private String performedBy;   // username or "cms-service"
    private String details;       // description of change
    private LocalDateTime timestamp;
}