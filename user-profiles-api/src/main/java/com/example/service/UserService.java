package com.example.service;

import com.example.dto.CreateUserProfileRequest;
import com.example.dto.UpdateUserProfileRequest;
import com.example.dto.UserProfileDto;

import java.util.List;

public interface UserService {
    UserProfileDto create(CreateUserProfileRequest request);
    UserProfileDto get(Long id);
    List<UserProfileDto> getAll();
    UserProfileDto update(Long id, UpdateUserProfileRequest request);
    void delete(Long id);
}
