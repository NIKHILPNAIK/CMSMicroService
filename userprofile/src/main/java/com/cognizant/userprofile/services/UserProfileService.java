package com.cognizant.userprofile.services;

import com.cognizant.userprofile.dtos.UserProfileRequest;
import com.cognizant.userprofile.dtos.UserProfileResponse;

import java.util.List;

public interface UserProfileService {
    UserProfileResponse create(UserProfileRequest request);
    UserProfileResponse getById(Long id);
    UserProfileResponse getByUsername(String username);
    List<UserProfileResponse> getAll();
    UserProfileResponse update(Long id, UserProfileRequest request);
    void delete(Long id);
}