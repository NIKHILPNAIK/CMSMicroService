package com.cognizant.audit.controller;

import com.cognizant.audit.dtos.AuditLogRequest;
import com.cognizant.audit.dtos.AuditLogResponse;
import com.cognizant.audit.services.AuditServiceImpl;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/audit")
public class AuditController {
	
	@Autowired
    private AuditServiceImpl service;

    /**
     * Simplest contract:
     *   POST /audit/log
     *   Body: AuditLogRequest
     *   Returns: { "id": <generated id> }
     */
    @PostMapping("/log")
    public ResponseEntity<AuditLogResponse> log(@Valid @RequestBody AuditLogRequest request) {
        Long id = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AuditLogResponse.builder().id(id).build());
    }
}