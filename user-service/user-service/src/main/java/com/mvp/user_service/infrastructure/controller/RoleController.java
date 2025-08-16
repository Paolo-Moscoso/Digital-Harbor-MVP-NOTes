package com.mvp.user_service.infrastructure.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvp.user_service.application.dto.RoleRequestDTO;
import com.mvp.user_service.application.dto.RoleResponseDTO;
import com.mvp.user_service.application.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Crear un nuevo role
    @PostMapping
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody RoleRequestDTO roleRequestDTO) {

        RoleResponseDTO role = roleService.createRole(roleRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }

    // Obtener role por id
    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> getRoleById(@PathVariable UUID id) {
        RoleResponseDTO role = roleService.getRoleById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(role);
    }

    // Obtener role por nombre
    @GetMapping("/name/{name}")
    public ResponseEntity<RoleResponseDTO> getRoleByName(@PathVariable String name) {
        RoleResponseDTO role = roleService.getRoleByName(name);
        return ResponseEntity.status(HttpStatus.FOUND).body(role);
    }

    // Listar todos los roles
    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {
        List<RoleResponseDTO> roles = roleService.getAllRoles();
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }
}