package com.example.mapper;

import com.example.dto.CreateUserProfileRequest;
import com.example.dto.UpdateUserProfileRequest;
import com.example.dto.UserProfileDto;
import com.example.model.Role;
import com.example.model.User;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(toRoles(createRequest.getRoles()))")
    User toEntity(CreateUserProfileRequest createRequest);

    @Mapping(target = "roles", expression = "java(toRoleStrings(user.getRoles()))")
    UserProfileDto toDto(User user);

    void updateEntityFromDto(UpdateUserProfileRequest update, @MappingTarget User user);

    default Set<Role> toRoles(Set<String> roleStrings) {
        if (roleStrings == null) return null;
        return roleStrings.stream().map(s -> Role.valueOf(s)).collect(Collectors.toSet());
    }

    default Set<String> toRoleStrings(Set<Role> roles) {
        if (roles == null) return null;
        return roles.stream().map(Enum::name).collect(Collectors.toSet());
    }
}
