package com.mvp.auth_service.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.mvp.auth_service.infrastructure.persistence.entity.AuthUserEntity;

public interface AuthUserRepositoryPort {
    Optional<AuthUserEntity> findByUsername(String username);

    Optional<AuthUserEntity> findByUserId(UUID userId);

    Boolean existsByUsername(String username);
}
