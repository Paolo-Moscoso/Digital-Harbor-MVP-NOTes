package com.mvp.user_service.application.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mvp.user_service.application.dto.UserRequestDTO;
import com.mvp.user_service.application.dto.UserResponseDTO;
import com.mvp.user_service.domain.model.RoleModel;
import com.mvp.user_service.domain.model.UserModel;
import com.mvp.user_service.domain.repository.RoleRepositoryPort;
import com.mvp.user_service.domain.repository.UserRepositoryPort;
import com.mvp.user_service.infrastructure.persistence.mapper.UserMapper;

@Service
public class UserService {

    private final UserRepositoryPort userRepository;
    private final RoleRepositoryPort roleRepository;

    public UserService(UserRepositoryPort userRepository, RoleRepositoryPort roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // Crear usuario
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        // se buscara el primero que coincida con el nombre del rol
        if (requestDTO.getRoleName() == null || requestDTO.getRoleName().isBlank()) {
            throw new IllegalArgumentException("El nombre del rol no puede estar vacío");
        }
        // Buscar el rol por nombre
        RoleModel roleOpt = roleRepository.findByName(requestDTO.getRoleName());
        if (roleOpt == null) {
            throw new IllegalArgumentException("Role no encontrado");
        }

        UserModel userModel = UserMapper.toDomain(requestDTO, roleOpt.id());
        UserModel savedUser = userRepository.save(userModel);
        return UserMapper.toResponseDTO(savedUser, roleOpt.name());
    }

    // Obtener usuario por id
    public UserResponseDTO getUserById(UUID id) {
        UserModel user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        String roleName = roleRepository.findById(user.roleId())
                .map(r -> r.name())
                .orElse("ROLE_DESCONOCIDO");

        return UserMapper.toResponseDTO(user, roleName);
    }

    // Actualizar usuario
    public UserResponseDTO updateUser(UUID id, UserRequestDTO requestDTO) {
        UserModel existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Actualizar campos
        UserModel updatedUser = new UserModel(
                existingUser.id(),
                requestDTO.getFirstName(),
                requestDTO.getLastName(),
                requestDTO.getPhoneNumber(),
                requestDTO.getAddress(),
                existingUser.active(),
                requestDTO.getRoleName() != null ? roleRepository.findByName(requestDTO.getRoleName()).id()
                        : existingUser.roleId());

        UserModel savedUser = userRepository.update(updatedUser);
        String roleName = roleRepository.findById(savedUser.roleId())
                .map(r -> r.name())
                .orElse("ROLE_DESCONOCIDO");

        return UserMapper.toResponseDTO(savedUser, roleName);
    }

    // Desactivar usuario
    public void deactivateUser(UUID id) {
        userRepository.deactivate(id);
    }

    // Listar todos los usuarios
    public List<UserResponseDTO> getAllUsers() {
        List<UserModel> users = userRepository.findAll();
        return users.stream()
                .map(u -> {
                    String roleName = roleRepository.findById(u.roleId())
                            .map(r -> r.name())
                            .orElse("ROLE_DESCONOCIDO");
                    return UserMapper.toResponseDTO(u, roleName);
                })
                .collect(Collectors.toList());
    }
}
