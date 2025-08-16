package com.mvp.user_service.infrastructure.persistence.mapper;

import com.mvp.user_service.application.dto.RoleRequestDTO;
import com.mvp.user_service.application.dto.RoleResponseDTO;
import com.mvp.user_service.domain.model.RoleModel;
import com.mvp.user_service.infrastructure.persistence.entity.RoleEntity;

public class RoleMapper {
    // RoleRequestDTO → RoleModel
    public static RoleModel toDomain(RoleRequestDTO dto) {
        return new RoleModel(
                null, // ID se genera al guardar
                dto.getName(),
                dto.getDescription());
    }

    // RoleEntity → RoleModel
    public static RoleModel toDomain(RoleEntity entity) {
        return new RoleModel(entity.getId(), entity.getName(), entity.getDescription());
    }

    // RoleModel → RoleEntity
    public static RoleEntity toEntity(RoleModel model) {
        RoleEntity entity = new RoleEntity();
        entity.setId(model.id());
        entity.setName(model.name());
        entity.setDescription(model.description());
        return entity;
    }

    // RoleModel → RoleResponseDTO
    public static RoleResponseDTO toResponseDTO(RoleModel model) {
        return new RoleResponseDTO(model.id(), model.name(), model.description());
    }

}
