package com.mvp.note_sevice.domain.repository;

import java.util.List;
import java.util.Optional;

import com.mvp.note_sevice.domain.model.NoteModel;

public interface NoteRepositoryPort {

    // 🔹 Guardar o actualizar una nota
    NoteModel save(NoteModel note);

    // 🔹 Buscar nota por ID
    Optional<NoteModel> findById(String id);

    // 🔹 Buscar notas por título + userId
    List<NoteModel> findByTitleAndUserId(String title, String userId);

    // 🔹 Buscar notas por coincidencia en el contenido
    List<NoteModel> findByContentContaining(String keyword, String userId);

    // 🔹 Buscar notas que contengan uno o varios tags (OR lógico)
    List<NoteModel> findByTags(List<String> tagIds, String userId);

    // 🔹 Buscar notas por estado de archivado y usuario
    List<NoteModel> findByUserIdAndArchived(String userId, boolean archived);

    // 🔹 Buscar todas las notas de un usuario
    List<NoteModel> findByUserId(String userId);

    // 🔹 Eliminar nota por ID
    void deleteById(String id);
}