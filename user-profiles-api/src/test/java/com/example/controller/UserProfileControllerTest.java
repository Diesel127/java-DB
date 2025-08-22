package com.example.controller;

import com.example.dto.UpdateUserProfileRequest;
import com.example.dto.UserProfileDto;
import com.example.security.JwtTokenProvider;
import com.example.security.SecurityUserDetailsService;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserProfileController.class)
class UserProfileControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    JwtTokenProvider tokenProvider;

    @MockBean
    SecurityUserDetailsService userDetailsService;

    @Test
    void get_Unauthorized_WithoutToken() throws Exception {
        mockMvc.perform(get("/api/profiles/1"))
                .andExpect(status().isUnauthorized());
    }
}
