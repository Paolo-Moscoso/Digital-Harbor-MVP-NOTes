package com.mvp.note_sevice.application.dto;

import java.time.Instant;
import java.util.List;

import lombok.Data;

@Data
public class NoteRequestDTO {
        private String id;
        private String userId;
        private String title;
        private String content;
        private List<String> tags;
        private boolean archived;
        private Instant createdAt;
        private Instant updatedAt;
}
