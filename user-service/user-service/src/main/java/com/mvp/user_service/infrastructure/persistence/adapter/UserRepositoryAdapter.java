package com.mvp.user_service.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mvp.user_service.domain.model.UserModel;
import com.mvp.user_service.domain.repository.UserRepositoryPort;
import com.mvp.user_service.infrastructure.persistence.entity.UserEntity;
import com.mvp.user_service.infrastructure.persistence.mapper.UserMapper;
import com.mvp.user_service.infrastructure.persistence.repository.UserRepositoryJpa;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepositoryJpa userRepositoryJpa;

    public UserRepositoryAdapter(UserRepositoryJpa userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Override
    public UserModel save(UserModel user) {
        UserEntity entity = UserMapper.toEntity(user);
        UserEntity saved = userRepositoryJpa.save(entity);
        return UserMapper.toDomain(saved);
    }

    @Override
    public Optional<UserModel> findById(UUID id) {
        return userRepositoryJpa.findById(id).map(UserMapper::toDomain);
    }

    @Override
    public List<UserModel> findAll() {
        return userRepositoryJpa.findAll().stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public UserModel update(UserModel user) {
        UserEntity entity = UserMapper.toEntity(user);
        UserEntity updated = userRepositoryJpa.save(entity);
        return UserMapper.toDomain(updated);
    }

    @Override
    public void deactivate(UUID id) {
        userRepositoryJpa.findById(id).ifPresent(user -> {
            user.setActive(false);
            userRepositoryJpa.save(user);
        });
    }

}
