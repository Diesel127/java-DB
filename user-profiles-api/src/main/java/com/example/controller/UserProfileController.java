package com.example.controller;

import com.example.dto.CreateUserProfileRequest;
import com.example.dto.UpdateUserProfileRequest;
import com.example.dto.UserProfileDto;
import com.example.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/profiles")
@Tag(name = "User Profiles")
@SecurityRequirement(name = "bearerAuth")
public class UserProfileController {

    private final UserService userService;

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new user (ADMIN only)")
    public ResponseEntity<UserProfileDto> create(@Valid @RequestBody CreateUserProfileRequest request) {
        UserProfileDto dto = userService.create(request);
        return ResponseEntity.created(URI.create("/api/profiles/" + dto.getId())).body(dto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@authz.canAccessUser(#id)")
    @Operation(summary = "Get a user by id (owner or ADMIN)")
    public ResponseEntity<UserProfileDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "List all users (ADMIN only)")
    public ResponseEntity<List<UserProfileDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PutMapping("/{id}")
    @PreAuthorize("@authz.canAccessUser(#id)")
    @Operation(summary = "Update a user (owner or ADMIN)")
    public ResponseEntity<UserProfileDto> update(@PathVariable Long id, @Valid @RequestBody UpdateUserProfileRequest request) {
        return ResponseEntity.ok(userService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a user (ADMIN only)")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
