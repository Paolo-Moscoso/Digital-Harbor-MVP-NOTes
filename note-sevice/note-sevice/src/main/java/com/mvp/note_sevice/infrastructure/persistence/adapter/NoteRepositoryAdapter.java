package com.mvp.note_sevice.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mvp.note_sevice.domain.model.NoteModel;
import com.mvp.note_sevice.domain.repository.NoteRepositoryPort;
import com.mvp.note_sevice.infrastructure.mapper.NoteMapper;
import com.mvp.note_sevice.infrastructure.persistence.entity.NoteEntity;
import com.mvp.note_sevice.infrastructure.persistence.repository.NoteRepository;

@Component
public class NoteRepositoryAdapter implements NoteRepositoryPort {

    private final NoteRepository noteRepository;

    public NoteRepositoryAdapter(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public NoteModel save(NoteModel note) {
        NoteEntity entity = NoteMapper.toEntity(note);
        NoteEntity saved = noteRepository.save(entity);
        return NoteMapper.toModel(saved);
    }

    @Override
    public Optional<NoteModel> findById(String id) {
        return noteRepository.findById(id)
                .map(NoteMapper::toModel);
    }

    @Override
    public List<NoteModel> findByTitleAndUserId(String title, String userId) {
        return noteRepository.findByTitleAndUserId(title, userId)
                .stream()
                .map(NoteMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<NoteModel> findByContentContaining(String keyword, String userId) {
        return noteRepository.findByContentContainingAndUserId(keyword, userId)
                .stream()
                .map(NoteMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<NoteModel> findByTags(List<String> tagIds, String userId) {
        return noteRepository.findByTagIdsInAndUserId(tagIds, userId)
                .stream()
                .map(NoteMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<NoteModel> findByUserIdAndArchived(String userId, boolean archived) {
        return noteRepository.findByUserIdAndArchived(userId, archived)
                .stream()
                .map(NoteMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<NoteModel> findByUserId(String userId) {
        return noteRepository.findByUserId(userId)
                .stream()
                .map(NoteMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        noteRepository.deleteById(id);
    }
}
