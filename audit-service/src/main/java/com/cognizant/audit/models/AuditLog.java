package com.cognizant.audit.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;       // FK value from CMS (not enforced here)
    private String action;         // CREATE / UPDATE / DELETE
    private String performedBy;    // user/service name
    @Column(length = 4000)
    private String details;        // free text or JSON

    private LocalDateTime timestamp;
}