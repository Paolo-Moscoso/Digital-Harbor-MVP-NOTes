package com.mvp.user_service.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mvp.user_service.domain.model.RoleModel;
import com.mvp.user_service.domain.repository.RoleRepositoryPort;
import com.mvp.user_service.infrastructure.persistence.entity.RoleEntity;
import com.mvp.user_service.infrastructure.persistence.mapper.RoleMapper;
import com.mvp.user_service.infrastructure.persistence.repository.RoleRepositoryJpa;

@Component
public class RoleRepositoryAdapter implements RoleRepositoryPort {

    private final RoleRepositoryJpa roleRepositoryJpa;

    public RoleRepositoryAdapter(RoleRepositoryJpa roleRepositoryJpa) {
        this.roleRepositoryJpa = roleRepositoryJpa;
    }

    @Override
    public RoleModel save(RoleModel role) {

        RoleEntity entity = RoleMapper.toEntity(role);
        return RoleMapper.toDomain(roleRepositoryJpa.save(entity));
    }

    @Override
    public Optional<RoleModel> findById(UUID id) {
        return roleRepositoryJpa.findById(id).map(RoleMapper::toDomain);
    }

    @Override
    public List<RoleModel> findAll() {
        return roleRepositoryJpa.findAll().stream()
                .map(RoleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public RoleModel findByName(String name) {
        return roleRepositoryJpa.findAll().stream()
                .filter(role -> role.getName().equalsIgnoreCase(name))
                .findFirst()
                .map(RoleMapper::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("Role not found with name: " + name));
    }
}