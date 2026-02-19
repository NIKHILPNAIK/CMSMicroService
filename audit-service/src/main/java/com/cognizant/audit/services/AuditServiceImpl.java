	package com.cognizant.audit.services;

import com.cognizant.audit.dtos.AuditLogRequest;
import com.cognizant.audit.models.AuditLog;
import com.cognizant.audit.repositories.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl {

    private final AuditLogRepository repo;

    public Long save(AuditLogRequest req) {
        AuditLog log = AuditLog.builder()
                .customerId(req.getCustomerId())
                .action(req.getAction())
                .performedBy(req.getPerformedBy())
                .details(req.getDetails())
                .timestamp(req.getTimestamp() != null ? req.getTimestamp() : LocalDateTime.now())
                .build();

        return repo.save(log).getId();
    }
}