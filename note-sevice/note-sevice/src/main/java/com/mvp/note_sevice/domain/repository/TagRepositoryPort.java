package com.mvp.note_sevice.domain.repository;

import java.util.List;
import java.util.Optional;

import com.mvp.note_sevice.domain.model.TagModel;

public interface TagRepositoryPort {

    // 🔹 Guardar o actualizar una etiqueta
    TagModel save(TagModel tag);

    // 🔹 Buscar etiqueta por ID
    Optional<TagModel> findById(String id);

    Optional<TagModel> findByName(String id);

    // 🔹 Buscar todas las etiquetas de un usuario
    List<TagModel> findByUserId(String userId);

    List<TagModel> findByAll();

    // 🔹 Buscar etiqueta por nombre y usuario (para evitar duplicados)
    Optional<TagModel> findByNameAndUserId(String name, String userId);

    List<TagModel> findByNameContaining(String name);

    List<TagModel> findByNameContainingAndUserId(String name, String userId);

    // 🔹 Eliminar etiqueta por ID
    void deleteById(String id);

}
