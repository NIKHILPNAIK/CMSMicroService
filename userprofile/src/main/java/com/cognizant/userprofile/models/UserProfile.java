package com.cognizant.userprofile.models;

import com.cognizant.userprofile.converters.JsonMapConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "user_profiles",uniqueConstraints = @UniqueConstraint(name = "uq_username", columnNames = "username"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=100, nullable=false)
    private String username;

    @Column(length=50, nullable=false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Lob
    @Column(columnDefinition = "TEXT")
    @Convert(converter = JsonMapConverter.class)
    private Map<String, Object> preferences; // persisted as JSON text

    
    @Column(name = "created_at", nullable=false)
    private LocalDateTime createdAt;
    
    
}