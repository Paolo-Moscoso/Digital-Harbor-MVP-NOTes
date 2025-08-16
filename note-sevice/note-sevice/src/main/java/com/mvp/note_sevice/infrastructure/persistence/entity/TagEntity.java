package com.mvp.note_sevice.infrastructure.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "tags")
@CompoundIndex(def = "{'name': 1, 'userId': 1}", unique = true) // deny duplicate tag names for the same user
@Data
public class TagEntity {

    @Id
    private String id;
    private String name;
    private String userId;
}