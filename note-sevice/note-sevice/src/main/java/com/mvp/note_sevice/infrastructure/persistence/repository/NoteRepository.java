package com.mvp.note_sevice.infrastructure.persistence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mvp.note_sevice.infrastructure.persistence.entity.NoteEntity;

@Repository
public interface NoteRepository extends MongoRepository<NoteEntity, String> {
    // Busca Notas donde userId coincida
    List<NoteEntity> findByUserId(String userId);

    // Busca Notas donde title and userId coincida
    List<NoteEntity> findByTitleAndUserId(String title, String userId);

    // donde content contenga la cadena (similar a LIKE) y userId = ?
    List<NoteEntity> findByContentContainingAndUserId(String keyword, String userId);

    // donde al menos un elemento de tagIds esté en la lista del documento y userId
    // = ?
    List<NoteEntity> findByTagIdsInAndUserId(List<String> tagIds, String userId);

    List<NoteEntity> findByUserIdAndArchived(String userId, boolean archived);
}