package com.cognizant.userprofile.controllers;

import com.cognizant.userprofile.dtos.UserProfileRequest;
import com.cognizant.userprofile.dtos.UserProfileResponse;
import com.cognizant.userprofile.services.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class UserProfileController {

	@Autowired
    private UserProfileService service;

    
    @PreAuthorize("hasAnyAuthority('SCOPE_developer')")
    @PostMapping
    public ResponseEntity<UserProfileResponse> create(@Valid @RequestBody UserProfileRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    
    @PreAuthorize("hasAnyAuthority('SCOPE_tester','SCOPE_developer')")
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    
    @PreAuthorize("hasAnyAuthority('SCOPE_tester','SCOPE_developer')")
    @GetMapping("/by-username/{username}")
    public ResponseEntity<UserProfileResponse> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(service.getByUsername(username));
    }

    // Read (all): GET /profiles – 200
    @PreAuthorize("hasAnyAuthority('SCOPE_developer')")
    @GetMapping
    public ResponseEntity<List<UserProfileResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // Update: PUT /profiles/{id} 
    @PreAuthorize("hasAnyAuthority('SCOPE_developer')")
    @PutMapping("/{id}")
    public ResponseEntity<UserProfileResponse> update(@PathVariable Long id,
                                                      @Valid @RequestBody UserProfileRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    // Delete: DELETE /profiles/{id} 
    @PreAuthorize("hasAnyAuthority('SCOPE_developer')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}