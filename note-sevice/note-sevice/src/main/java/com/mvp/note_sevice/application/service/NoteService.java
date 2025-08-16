package com.mvp.note_sevice.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mvp.note_sevice.application.dto.NoteRequestDTO;
import com.mvp.note_sevice.application.dto.NoteResponseDTO;
import com.mvp.note_sevice.domain.model.NoteModel;
import com.mvp.note_sevice.domain.model.TagModel;
import com.mvp.note_sevice.domain.repository.NoteRepositoryPort;
import com.mvp.note_sevice.domain.repository.TagRepositoryPort;
import com.mvp.note_sevice.infrastructure.mapper.NoteMapper;

@Service
public class NoteService {

    private final NoteRepositoryPort noteRepositoryPort;
    private final TagRepositoryPort tagRepositoryPort;

    public NoteService(NoteRepositoryPort noteRepositoryPort, TagRepositoryPort tagRepositoryPort) {
        this.noteRepositoryPort = noteRepositoryPort;
        this.tagRepositoryPort = tagRepositoryPort;
    }

    // Crear o actualizar una nota
    public NoteResponseDTO saveNote(NoteRequestDTO noteRequestDTO) {
        List<String> tagNames = noteRequestDTO.getTags();
        List<String> tagIds = tagNames.stream()
                .map(tagName -> {
                    Optional<TagModel> tagOpt = tagRepositoryPort.findByName(tagName);
                    if (tagOpt.isPresent()) {
                        return tagOpt.get().id();
                    } else {
                        TagModel newTag = new TagModel(null, tagName, noteRequestDTO.getUserId());
                        TagModel savedTag = tagRepositoryPort.save(newTag);
                        return savedTag.id();
                    }
                })
                .collect(Collectors.toList());

        NoteModel noteModel = NoteMapper.toModel(noteRequestDTO, tagIds);

        NoteModel savedNote = noteRepositoryPort.save(noteModel);
        return NoteMapper.toResponseDTO(savedNote, tagNames);
    }

    // update Note
    public NoteResponseDTO updateNote(String id, NoteRequestDTO noteRequestDTO) {
        return noteRepositoryPort.findById(id)
                .map(existingNote -> {
                    NoteModel updatedNote = NoteMapper.toModel(noteRequestDTO, existingNote.tagsId());
                    NoteModel savedNote = noteRepositoryPort.save(updatedNote);
                    return NoteMapper.toResponseDTO(savedNote, getTagNamesByIds(savedNote.tagsId()));
                })
                .orElseThrow(() -> new RuntimeException("Note not found"));
    }

    // Obtener una nota por ID
    public Optional<NoteResponseDTO> getNoteById(String id) {
        return noteRepositoryPort.findById(id)
                .map(noteModel -> NoteMapper.toResponseDTO(noteModel, getTagNamesByIds(noteModel.tagsId())));
    }

    // Obtener todas las notas de un usuario
    public List<NoteResponseDTO> getAllNotesByUser(String userId) {
        return noteRepositoryPort.findByUserId(userId).stream()
                .map(noteModel -> NoteMapper.toResponseDTO(noteModel, getTagNamesByIds(noteModel.tagsId())))
                .collect(Collectors.toList());
    }

    // Buscar notas por título y usuario
    public List<NoteResponseDTO> getNotesByTitle(String title, String userId) {
        return noteRepositoryPort.findByTitleAndUserId(title, userId).stream()
                .map(noteModel -> NoteMapper.toResponseDTO(noteModel, getTagNamesByIds(noteModel.tagsId())))
                .collect(Collectors.toList());
    }

    // Buscar notas por contenido
    public List<NoteResponseDTO> searchNotesByContent(String keyword, String userId) {
        return noteRepositoryPort.findByContentContaining(keyword, userId).stream()
                .map(noteModel -> NoteMapper.toResponseDTO(noteModel, getTagNamesByIds(noteModel.tagsId())))
                .collect(Collectors.toList());
    }

    // Buscar notas por tags
    public List<NoteResponseDTO> getNotesByTags(List<String> tagIds, String userId) {
        return noteRepositoryPort.findByTags(tagIds, userId).stream()
                .map(noteModel -> NoteMapper.toResponseDTO(noteModel, getTagNamesByIds(noteModel.tagsId())))
                .collect(Collectors.toList());
    }

    // Buscar notas archivadas por usuario
    public List<NoteResponseDTO> getArchivedNotes(String userId) {
        return noteRepositoryPort.findByUserIdAndArchived(userId, true).stream()
                .map(noteModel -> NoteMapper.toResponseDTO(noteModel, getTagNamesByIds(noteModel.tagsId())))
                .collect(Collectors.toList());
    }

    // Eliminar nota por ID
    public void deleteNote(String id) {
        noteRepositoryPort.deleteById(id);
    }

    // Obtener nombres de tags por sus IDs
    private List<String> getTagNamesByIds(List<String> tagIds) {
        return tagIds.stream()
                .map(tagRepositoryPort::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(TagModel::name)
                .collect(Collectors.toList());
    }

}
