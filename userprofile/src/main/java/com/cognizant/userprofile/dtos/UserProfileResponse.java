package com.cognizant.userprofile.dtos;

import com.cognizant.userprofile.models.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponse {
    private Long id;
    private String username;
    

 @Schema(
        description = "User role (enum)",
        example = "ADMIN",
        allowableValues = {"ADMIN","USER"}   
    )
    private Role role;


    @Schema(
        description = "User preference settings",
        example = "{ \"theme\": \"dark\", \"notifications\": true }"
    )
    private Map<String, Object> preferences;
    private LocalDateTime createdAt;
}