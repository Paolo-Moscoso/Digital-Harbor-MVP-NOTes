package com.mvp.user_service.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.mvp.user_service.domain.model.UserModel;

public interface UserRepositoryPort {
    UserModel save(UserModel user);

    Optional<UserModel> findById(UUID id);

    List<UserModel> findAll();

    UserModel update(UserModel user);

    void deactivate(UUID id);
}