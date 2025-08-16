package com.mvp.user_service.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mvp.user_service.domain.model.RoleModel;

public interface RoleRepositoryPort {
    RoleModel save(RoleModel role);

    Optional<RoleModel> findById(UUID id);

    List<RoleModel> findAll();

    RoleModel findByName(String name);
}