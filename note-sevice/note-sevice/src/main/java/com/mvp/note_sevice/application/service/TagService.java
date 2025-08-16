package com.mvp.note_sevice.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mvp.note_sevice.application.dto.TagRequestDTO;
import com.mvp.note_sevice.application.dto.TagResponseDTO;
import com.mvp.note_sevice.domain.model.TagModel;
import com.mvp.note_sevice.domain.repository.TagRepositoryPort;
import com.mvp.note_sevice.infrastructure.mapper.TagMapper;

@Service
public class TagService {

    private final TagRepositoryPort tagRepositoryPort;

    public TagService(TagRepositoryPort tagRepositoryPort) {
        this.tagRepositoryPort = tagRepositoryPort;
    }

    // Crear una nueva etiqueta
    public TagResponseDTO createTag(TagRequestDTO tagRequestDTO) {
        Optional<TagModel> existingTag;
        if (tagRequestDTO.userId() != null) {
            existingTag = tagRepositoryPort.findByNameAndUserId(tagRequestDTO.name(), tagRequestDTO.userId());
        } else {
            existingTag = tagRepositoryPort.findByName(tagRequestDTO.name());
        }

        if (existingTag.isPresent()) {
            return TagMapper.toResponseDTO(existingTag.get());
        }

        return TagMapper.toResponseDTO(tagRepositoryPort.save(TagMapper.toModel(tagRequestDTO)));
    }

    public List<TagResponseDTO> getAllTags(String userId) {
        if (userId != null) {
            return tagRepositoryPort.findByUserId(userId).stream()
                    .map(TagMapper::toResponseDTO)
                    .collect(Collectors.toList());
        }
        return tagRepositoryPort.findByAll().stream()
                .map(TagMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Búsqueda dinámica de etiquetas (autocomplete)
    public List<TagResponseDTO> searchTags(String keyword, String userId) {
        if (userId != null) {
            return tagRepositoryPort.findByNameContainingAndUserId(keyword, userId)
                    .stream()
                    .map(TagMapper::toResponseDTO)
                    .collect(Collectors.toList());
        }
        return tagRepositoryPort.findByNameContaining(keyword)
                .stream()
                .map(TagMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Eliminar etiqueta
    public void deleteTag(String tagId) {
        tagRepositoryPort.deleteById(tagId);
    }

}