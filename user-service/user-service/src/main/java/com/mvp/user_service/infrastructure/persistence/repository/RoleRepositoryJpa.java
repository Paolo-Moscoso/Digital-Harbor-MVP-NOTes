package com.mvp.user_service.infrastructure.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mvp.user_service.infrastructure.persistence.entity.RoleEntity;

@Repository
public interface RoleRepositoryJpa extends JpaRepository<RoleEntity, UUID> {
}
