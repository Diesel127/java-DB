package com.example.service;

import com.example.dto.CreateUserProfileRequest;
import com.example.dto.UpdateUserProfileRequest;
import com.example.dto.UserProfileDto;
import com.example.mapper.UserMapper;
import com.example.model.Role;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;
    private UserServiceImpl service;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        userMapper = mock(UserMapper.class);
        passwordEncoder = mock(PasswordEncoder.class);
        service = new UserServiceImpl(userRepository, userMapper, passwordEncoder);
    }

    @Test
    void create_ShouldEncodePassword_AndSave() {
        CreateUserProfileRequest req = new CreateUserProfileRequest();
        req.setEmail("john@example.com");
        req.setPassword("secret123");
        req.setFullName("John Doe");
        req.setRoles(Set.of("ROLE_USER"));

        User entity = new User();
        entity.setEmail(req.getEmail());
        entity.setPassword(req.getPassword());
        entity.setFullName(req.getFullName());
        entity.setRoles(Set.of(Role.ROLE_USER));

        when(userRepository.existsByEmail(req.getEmail())).thenReturn(false);
        when(userMapper.toEntity(req)).thenReturn(entity);
        when(passwordEncoder.encode("secret123")).thenReturn("ENCODED");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));
        UserProfileDto dto = new UserProfileDto();
        dto.setEmail(req.getEmail());
        when(userMapper.toDto(any(User.class))).thenReturn(dto);

        UserProfileDto result = service.create(req);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        assertEquals("ENCODED", captor.getValue().getPassword());
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    void update_ShouldMapFields() {
        UpdateUserProfileRequest update = new UpdateUserProfileRequest();
        update.setFullName("New Name");

        User user = new User();
        user.setId(1L);
        user.setFullName("Old Name");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(new UserProfileDto());

        UserProfileDto result = service.update(1L, update);
        verify(userMapper).updateEntityFromDto(update, user);
        assertNotNull(result);
    }
}
