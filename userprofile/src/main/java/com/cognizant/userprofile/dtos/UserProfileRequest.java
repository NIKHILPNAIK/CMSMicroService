package com.cognizant.userprofile.dtos;

import com.cognizant.userprofile.models.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileRequest {

    @NotBlank @Size(max=100)
    private String username;

    @NotNull
    @Schema(
        description = "User role (enum)",
        example = "ADMIN",
        allowableValues = {"ADMIN","USER"}  
    )
    private Role role;

    // free-form JSON

 @Schema(
        description = "User preference settings",
        example = "{ \"theme\": \"dark\", \"notifications\": true }"
    )
    private Map<String, Object> preferences;

    // Optional: allow the client to set createdAt; else server sets now()
    private LocalDateTime createdAt;
}