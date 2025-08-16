package com.mvp.auth_service.application.dto;

public record RegisterRequest(
        String username,
        String password,
        String firstName,
        String lastName,
        String phoneNumber,
        String address,
        String roleName) {
}