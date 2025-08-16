package com.mvp.user_service.domain.model;

import java.util.UUID;

public record RoleModel(
        UUID id,
        String name,
        String description) {
}
