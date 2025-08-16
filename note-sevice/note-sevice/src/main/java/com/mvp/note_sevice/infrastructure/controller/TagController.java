package com.mvp.note_sevice.infrastructure.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvp.note_sevice.application.dto.TagRequestDTO;
import com.mvp.note_sevice.application.dto.TagResponseDTO;
import com.mvp.note_sevice.application.service.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    // Crear una nueva etiqueta
    @PostMapping
    public ResponseEntity<TagResponseDTO> createTag(@RequestBody TagRequestDTO tagRequestDTO) {
        TagResponseDTO tag = tagService.createTag(tagRequestDTO);
        return ResponseEntity.ok(tag);
    }

    // Obtener todas las etiquetas (por usuario o todas)
    @GetMapping
    public ResponseEntity<List<TagResponseDTO>> getAllTags(@RequestParam(required = false) String userId) {
        List<TagResponseDTO> tags = tagService.getAllTags(userId);
        return ResponseEntity.ok(tags);
    }

    // Buscar etiquetas por keyword (autocomplete)
    @GetMapping("/search")
    public ResponseEntity<List<TagResponseDTO>> searchTags(
            @RequestParam String keyword,
            @RequestParam(required = false) String userId) {
        List<TagResponseDTO> tags = tagService.searchTags(keyword, userId);
        return ResponseEntity.ok(tags);
    }

    // Eliminar etiqueta por ID
    @DeleteMapping("/{tagId}")
    public ResponseEntity<Void> deleteTag(@PathVariable String tagId) {
        tagService.deleteTag(tagId);
        return ResponseEntity.noContent().build();
    }
}
