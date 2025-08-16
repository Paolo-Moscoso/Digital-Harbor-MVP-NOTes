package com.mvp.auth_service.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mvp.auth_service.domain.repository.AuthUserRepositoryPort;
import com.mvp.auth_service.infrastructure.persistence.entity.AuthUserEntity;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUserEntity, Long>, AuthUserRepositoryPort {

}