package com.mvp.user_service.application.dto;

import java.util.UUID;

public record RoleResponseDTO(
                UUID id,
                String name,
                String description) {
}
