package com.mvp.note_sevice.application.dto;

public record TagRequestDTO(String name, String userId) {

    public TagRequestDTO {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("User ID cannot be null or blank");
        }
    }

}
