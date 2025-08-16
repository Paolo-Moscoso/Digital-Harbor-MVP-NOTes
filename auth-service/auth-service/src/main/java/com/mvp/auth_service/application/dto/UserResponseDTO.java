package com.mvp.auth_service.application.dto;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String firstName,
        String lastName,
        String phoneNumber,
        String address,
        String roleName,
        boolean active) {
}
