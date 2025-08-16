package com.mvp.note_sevice.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mvp.note_sevice.domain.model.TagModel;
import com.mvp.note_sevice.domain.repository.TagRepositoryPort;
import com.mvp.note_sevice.infrastructure.mapper.TagMapper;
import com.mvp.note_sevice.infrastructure.persistence.entity.TagEntity;
import com.mvp.note_sevice.infrastructure.persistence.repository.TagRepository;

@Component
public class TagRepositoryAdapter implements TagRepositoryPort {

    private final TagRepository tagRepository;

    public TagRepositoryAdapter(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public TagModel save(TagModel tag) {
        TagEntity entity = TagMapper.toEntity(tag);
        TagEntity saved = tagRepository.save(entity);
        return TagMapper.toModel(saved);
    }

    @Override
    public Optional<TagModel> findById(String id) {
        return tagRepository.findById(id)
                .map(TagMapper::toModel);
    }

    @Override
    public Optional<TagModel> findByName(String name) {
        return tagRepository.findByName(name)
                .map(TagMapper::toModel);
    }

    @Override
    public List<TagModel> findByUserId(String userId) {
        return tagRepository.findByUserId(userId)
                .stream()
                .map(TagMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TagModel> findByNameAndUserId(String name, String userId) {
        return tagRepository.findByNameAndUserId(name, userId)
                .map(TagMapper::toModel);
    }

    @Override
    public List<TagModel> findByNameContaining(String name) {
        return tagRepository.findByNameContaining(name)
                .stream()
                .map(TagMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        tagRepository.deleteById(id);
    }

    @Override
    public List<TagModel> findByAll() {
        return tagRepository.findAll()
                .stream()
                .map(TagMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<TagModel> findByNameContainingAndUserId(String name, String userId) {
        return tagRepository.findByNameContainingAndUserId(name, userId)
                .stream()
                .map(TagMapper::toModel)
                .collect(Collectors.toList());
    }
}