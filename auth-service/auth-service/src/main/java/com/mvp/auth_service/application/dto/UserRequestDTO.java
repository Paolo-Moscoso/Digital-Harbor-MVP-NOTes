package com.mvp.auth_service.application.dto;

public record UserRequestDTO(
        String firstName,
        String lastName,
        String phoneNumber,
        String address,
        String roleName) {
}