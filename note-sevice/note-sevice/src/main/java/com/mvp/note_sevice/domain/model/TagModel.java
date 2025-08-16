package com.mvp.note_sevice.domain.model;

public record TagModel(String id, String name, String userId) {
    public TagModel {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("Color cannot be null or blank");
        }
    }

}