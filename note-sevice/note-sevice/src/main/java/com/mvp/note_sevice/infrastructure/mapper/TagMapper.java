package com.mvp.note_sevice.infrastructure.mapper;

import com.mvp.note_sevice.application.dto.TagRequestDTO;
import com.mvp.note_sevice.application.dto.TagResponseDTO;
import com.mvp.note_sevice.domain.model.TagModel;
import com.mvp.note_sevice.infrastructure.persistence.entity.TagEntity;

public class TagMapper {

    // Converts TagRequestDTO to TagModel
    public static TagModel toModel(TagRequestDTO dto) {
        return new TagModel(null, dto.name(), dto.userId());
    }

    // Converts TagModel to TagEntity
    public static TagEntity toEntity(TagModel model) {
        TagEntity entity = new TagEntity();
        entity.setId(model.id());
        entity.setName(model.name());
        entity.setUserId(model.userId());
        return entity;
    }

    // Converts TagEntity to TagModel
    public static TagModel toModel(TagEntity entity) {
        return new TagModel(
                entity.getId(),
                entity.getName(),
                entity.getUserId());
    }

    // Converts TagModel to TagResponseDTO
    public static TagResponseDTO toResponseDTO(TagModel model) {
        return new TagResponseDTO(
                model.id(),
                model.name(),
                model.userId());
    }

}
