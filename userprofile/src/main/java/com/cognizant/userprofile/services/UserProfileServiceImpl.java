package com.cognizant.userprofile.services;

import com.cognizant.userprofile.dtos.UserProfileRequest;
import com.cognizant.userprofile.dtos.UserProfileResponse;
import com.cognizant.userprofile.exceptions.UserProfileNotFoundException;
import com.cognizant.userprofile.exceptions.UserProfileNullException;
import com.cognizant.userprofile.mappers.UserProfileMapper;
import com.cognizant.userprofile.models.UserProfile;
import com.cognizant.userprofile.repositories.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repo;
    private final UserProfileMapper mapper;

    @Override
    @Transactional
    public UserProfileResponse create(UserProfileRequest request) {
        if (request == null) {
            throw new UserProfileNullException("User profile payload is null");
        }
        if (repo.existsByUsername(request.getUsername())) {
            // 422 via GlobalExceptionHandler
            throw new IllegalArgumentException("Username already exists: " + request.getUsername());
        }
        UserProfile entity = mapper.toEntity(request);
        entity = repo.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    public UserProfileResponse getById(Long id) {
        UserProfile entity = repo.findById(id)
                .orElseThrow(() -> new UserProfileNotFoundException("User profile not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    public UserProfileResponse getByUsername(String username) {
        UserProfile entity = repo.findByUsername(username)
                .orElseThrow(() -> new UserProfileNotFoundException("User profile not found with username: " + username));
        return mapper.toResponse(entity);
    }

    @Override
    public List<UserProfileResponse> getAll() {
        return mapper.toResponses(repo.findAll());
    }

    @Override
    @Transactional
    public UserProfileResponse update(Long id, UserProfileRequest request) {
        if (request == null) {
            throw new UserProfileNullException("User profile payload is null");
        }
        UserProfile entity = repo.findById(id)
                .orElseThrow(() -> new UserProfileNotFoundException("User profile not found with id: " + id));

        // If username changes, ensure uniqueness
        if (request.getUsername() != null && !request.getUsername().equals(entity.getUsername())) {
            if (repo.existsByUsername(request.getUsername())) {
                throw new IllegalArgumentException("Username already exists: " + request.getUsername());
            }
        }

        mapper.updateEntityFromRequest(request, entity);
        entity = repo.save(entity);
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new UserProfileNotFoundException("User profile not found with id: " + id);
        }
        repo.deleteById(id);
    }
}