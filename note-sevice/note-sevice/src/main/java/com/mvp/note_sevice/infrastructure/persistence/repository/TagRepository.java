package com.mvp.note_sevice.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mvp.note_sevice.infrastructure.persistence.entity.TagEntity;

@Repository
public interface TagRepository extends MongoRepository<TagEntity, String> {
    List<TagEntity> findByUserId(String userId);

    Optional<TagEntity> findByNameAndUserId(String name, String userId);

    Optional<TagEntity> findByName(String name);

    List<TagEntity> findByNameContaining(String name);

    List<TagEntity> findByNameContainingAndUserId(String name, String userId);

}
