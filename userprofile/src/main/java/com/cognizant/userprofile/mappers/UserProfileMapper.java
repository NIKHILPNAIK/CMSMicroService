package com.cognizant.userprofile.mappers;

import com.cognizant.userprofile.dtos.UserProfileRequest;
import com.cognizant.userprofile.dtos.UserProfileResponse;
import com.cognizant.userprofile.models.UserProfile;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    /* ENTITY -> RESPONSE */
    @Mapping(source = "id",         target = "id")
    @Mapping(source = "username",   target = "username")
    @Mapping(source = "role",       target = "role")
    @Mapping(source = "preferences",target = "preferences")
    @Mapping(source = "createdAt",  target = "createdAt")
    UserProfileResponse toResponse(UserProfile entity);

    
    List<UserProfileResponse> toResponses(List<UserProfile> entities);

    /* REQUEST -> ENTITY (CREATE) */
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "username",   target = "username")
    @Mapping(source = "role",       target = "role")
    @Mapping(source = "preferences",target = "preferences")
    @Mapping(target = "createdAt",  expression = "java( request.getCreatedAt() != null ? request.getCreatedAt() : java.time.LocalDateTime.now() )")
    UserProfile toEntity(UserProfileRequest request);

    
    
    /* REQUEST -> ENTITY (PARTIAL UPDATE, ignore nulls) */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(UserProfileRequest request, @MappingTarget UserProfile entity);
}