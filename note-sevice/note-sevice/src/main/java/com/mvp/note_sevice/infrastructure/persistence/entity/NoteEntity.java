package com.mvp.note_sevice.infrastructure.persistence.entity;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "notes")
@Data
public class NoteEntity {
    @Id
    private String id;
    private String userId;
    private String title;
    private String content;
    private List<String> tagIds; // IDs de etiquetas
    private boolean archived;
    private Instant createdAt;
    private Instant updatedAt;

}
