package com.mvp.user_service.infrastructure.persistence.mapper;

import java.util.UUID;

import com.mvp.user_service.application.dto.UserRequestDTO;
import com.mvp.user_service.application.dto.UserResponseDTO;
import com.mvp.user_service.domain.model.UserModel;
import com.mvp.user_service.infrastructure.persistence.entity.UserEntity;

public class UserMapper {

    // UserRequestDTO → UserModel
    public static UserModel toDomain(UserRequestDTO dto, UUID roleId) {
        return new UserModel(
                null, // ID se genera al guardar
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPhoneNumber(),
                dto.getAddress(),
                true, // Activo por defecto
                roleId);
    }

    // UserModel → UserEntity
    public static UserEntity toEntity(UserModel model) {
        UserEntity entity = new UserEntity();
        entity.setId(model.id());
        entity.setFirstName(model.firstName());
        entity.setLastName(model.lastName());
        entity.setPhoneNumber(model.phoneNumber());
        entity.setAddress(model.address());
        entity.setRoleId(model.roleId());
        entity.setActive(model.active());
        return entity;
    }

    // UserEntity → UserModel
    public static UserModel toDomain(UserEntity entity) {
        return new UserModel(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhoneNumber(),
                entity.getAddress(),
                entity.isActive(),
                entity.getRoleId());
    }

    // UserModel → UserResponseDTO
    public static UserResponseDTO toResponseDTO(UserModel model, String roleName) {
        return new UserResponseDTO(
                model.id(),
                model.firstName(),
                model.lastName(),
                model.phoneNumber(),
                model.address(),
                roleName,
                model.active());
    }

}
