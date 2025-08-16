package com.mvp.auth_service.application.service;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mvp.auth_service.application.dto.LoginRequest;
import com.mvp.auth_service.application.dto.LoginResponse;
import com.mvp.auth_service.application.dto.RegisterRequest;
import com.mvp.auth_service.infrastructure.persistence.entity.AuthUserEntity;
import com.mvp.auth_service.infrastructure.persistence.repository.AuthUserRepository;
import com.mvp.auth_service.infrastructure.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthUserRepository authUserRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String register(RegisterRequest request, UUID userId) {
        if (authUserRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Username already exists");
        }

        AuthUserEntity user = AuthUserEntity.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .userId(userId)
                .build();

        authUserRepository.save(user);
        return "User registered successfully";
    }

    public LoginResponse login(LoginRequest request) {
        AuthUserEntity user = authUserRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtTokenProvider.generateToken(user.getUsername(), user.getUserId());
        return new LoginResponse(token);
    }
}
