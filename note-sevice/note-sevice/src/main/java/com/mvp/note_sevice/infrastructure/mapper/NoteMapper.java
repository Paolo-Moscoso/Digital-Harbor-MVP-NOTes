package com.mvp.note_sevice.infrastructure.mapper;

import java.util.List;

import com.mvp.note_sevice.application.dto.NoteRequestDTO;
import com.mvp.note_sevice.application.dto.NoteResponseDTO;
import com.mvp.note_sevice.domain.model.NoteModel;
import com.mvp.note_sevice.infrastructure.persistence.entity.NoteEntity;

public class NoteMapper {

    // NoteRequestDTO to Note Model mapping
    public static NoteModel toModel(NoteRequestDTO dto) {
        // Mapping logic
        return new NoteModel(
                null, // NoteRequestDTO does not have id(), set to null or generate as needed
                dto.getUserId(),
                dto.getTitle(),
                dto.getContent(),
                dto.getTags(),
                dto.isArchived(),
                dto.getCreatedAt(),
                dto.getUpdatedAt());
    }

    public static NoteModel toModel(NoteRequestDTO dto, List<String> tagIds) {
        // Mapping logic
        return new NoteModel(
                null, // NoteRequestDTO does not have id(), set to null or generate as needed
                dto.getUserId(),
                dto.getTitle(),
                dto.getContent(),
                tagIds,
                dto.isArchived(),
                dto.getCreatedAt(),
                dto.getUpdatedAt());
    }

    // Note Model to Note Entity mapping
    public static NoteEntity toEntity(NoteModel model) {
        NoteEntity entity = new NoteEntity();
        entity.setId(model.id());
        entity.setUserId(model.userId());
        entity.setTitle(model.title());
        entity.setContent(model.content());
        entity.setTagIds(model.tagsId());
        entity.setArchived(model.archived());
        entity.setCreatedAt(model.createdAt());
        entity.setUpdatedAt(model.updatedAt());
        return entity;
    }

    // Note Entity to Note Model mapping
    public static NoteModel toModel(NoteEntity entity) {
        return new NoteModel(
                entity.getId(),
                entity.getUserId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getTagIds(),
                entity.isArchived(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    // Note Model to NoteResponseDTO mapping
    // toResponseDTO need to add tags name instead of tagsId
    public static NoteResponseDTO toResponseDTO(NoteModel model, List<String> tags) {
        return new NoteResponseDTO(
                model.id(),
                model.userId(),
                model.title(),
                model.content(),
                tags, // Assuming tags is a list of tag names corresponding to tag IDs
                model.archived(),
                model.createdAt(),
                model.updatedAt());
    }

}
