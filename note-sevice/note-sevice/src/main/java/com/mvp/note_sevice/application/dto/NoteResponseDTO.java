package com.mvp.note_sevice.application.dto;

import java.time.Instant;
import java.util.List;

public record NoteResponseDTO(
        String id,
        String userId,
        String title,
        String content,
        List<String> tags,
        boolean archived,
        Instant createdAt,
        Instant updatedAt) {
}
