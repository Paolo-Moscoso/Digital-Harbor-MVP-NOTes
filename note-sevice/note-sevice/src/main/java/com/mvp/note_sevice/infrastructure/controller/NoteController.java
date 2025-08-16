package com.mvp.note_sevice.infrastructure.controller;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvp.note_sevice.application.dto.NoteRequestDTO;
import com.mvp.note_sevice.application.dto.NoteResponseDTO;
import com.mvp.note_sevice.application.service.NoteService;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // Crear o actualizar una nota
    @PostMapping
    public ResponseEntity<NoteResponseDTO> saveNote(@RequestBody NoteRequestDTO noteRequestDTO) {
        noteRequestDTO.setCreatedAt(Instant.now());
        noteRequestDTO.setUpdatedAt(Instant.now());
        NoteResponseDTO note = noteService.saveNote(noteRequestDTO);

        return ResponseEntity.ok(note);
    }

    // Actualizar una nota
    @PutMapping("/{id}")
    public ResponseEntity<NoteResponseDTO> updateNote(@PathVariable String id,
            @RequestBody NoteRequestDTO noteRequestDTO) {
        // actualizar el campo updatedAt crea otro requestDTO
        noteRequestDTO.setUpdatedAt(Instant.now());

        NoteResponseDTO updatedNote = noteService.updateNote(id, noteRequestDTO);
        return ResponseEntity.ok(updatedNote);
    }

    // Obtener una nota por ID
    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDTO> getNoteById(@PathVariable String id) {
        Optional<NoteResponseDTO> noteOpt = noteService.getNoteById(id);
        return noteOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener todas las notas de un usuario
    @GetMapping
    public ResponseEntity<List<NoteResponseDTO>> getAllNotesByUser(@RequestParam String userId) {
        List<NoteResponseDTO> notes = noteService.getAllNotesByUser(userId);
        return ResponseEntity.ok(notes);
    }

    // Buscar notas por título y usuario
    @GetMapping("/search/title")
    public ResponseEntity<List<NoteResponseDTO>> getNotesByTitle(
            @RequestParam String title,
            @RequestParam String userId) {
        List<NoteResponseDTO> notes = noteService.getNotesByTitle(title, userId);
        return ResponseEntity.ok(notes);
    }

    // Buscar notas por contenido
    @GetMapping("/search/content")
    public ResponseEntity<List<NoteResponseDTO>> searchNotesByContent(
            @RequestParam String keyword,
            @RequestParam String userId) {
        List<NoteResponseDTO> notes = noteService.searchNotesByContent(keyword, userId);
        return ResponseEntity.ok(notes);
    }

    // Buscar notas por tags
    @GetMapping("/search/tags")
    public ResponseEntity<List<NoteResponseDTO>> getNotesByTags(
            @RequestParam List<String> tagIds,
            @RequestParam String userId) {
        List<NoteResponseDTO> notes = noteService.getNotesByTags(tagIds, userId);
        return ResponseEntity.ok(notes);
    }

    // Buscar notas archivadas por usuario
    @GetMapping("/archived")
    public ResponseEntity<List<NoteResponseDTO>> getArchivedNotes(@RequestParam String userId) {
        List<NoteResponseDTO> notes = noteService.getArchivedNotes(userId);
        return ResponseEntity.ok(notes);
    }

    // Eliminar nota por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
