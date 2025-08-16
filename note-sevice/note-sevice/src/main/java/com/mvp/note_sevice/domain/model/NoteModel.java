package com.mvp.note_sevice.domain.model;

import java.time.Instant;
import java.util.List;

public record NoteModel(String id,
        String userId,
        String title,
        String content,
        List<String> tagsId,
        boolean archived,
        Instant createdAt,
        Instant updatedAt) {

}
