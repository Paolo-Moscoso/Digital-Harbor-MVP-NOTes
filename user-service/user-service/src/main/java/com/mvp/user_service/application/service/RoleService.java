package com.mvp.user_service.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mvp.user_service.application.dto.RoleRequestDTO;
import com.mvp.user_service.application.dto.RoleResponseDTO;
import com.mvp.user_service.domain.model.RoleModel;
import com.mvp.user_service.domain.repository.RoleRepositoryPort;
import com.mvp.user_service.infrastructure.persistence.mapper.RoleMapper;

@Service
public class RoleService {

    private final RoleRepositoryPort roleRepository;

    public RoleService(RoleRepositoryPort roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Crear nuevo role
    public RoleResponseDTO createRole(RoleRequestDTO requestDTO) {

        RoleModel role = RoleMapper.toDomain(requestDTO);
        return RoleMapper.toResponseDTO(roleRepository.save(role));
    }

    // Obtener role por id
    public RoleResponseDTO getRoleById(UUID id) {
        // RoleModel role = roleRepository.findById(id)
        // .orElseThrow(() -> new IllegalArgumentException("Role no encontrado"));
        // return RoleMapper.toResponseDTO(role);
        return roleRepository.findById(id)
                .map(RoleMapper::toResponseDTO)
                .orElseThrow(() -> new IllegalArgumentException("Role no encontrado con id: " + id));
    }

    // Obtener role por nombre
    public RoleResponseDTO getRoleByName(String name) {
        RoleModel role = roleRepository.findByName(name);
        if (role == null) {
            throw new IllegalArgumentException("Role no encontrado con nombre: " + name);
        }
        return RoleMapper.toResponseDTO(role);
    }

    // Listar todos los roles
    public List<RoleResponseDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(RoleMapper::toResponseDTO)
                .toList();
    }
}