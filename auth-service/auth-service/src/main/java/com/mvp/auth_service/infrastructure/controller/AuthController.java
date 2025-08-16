package com.mvp.auth_service.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvp.auth_service.application.dto.LoginRequest;
import com.mvp.auth_service.application.dto.LoginResponse;
import com.mvp.auth_service.application.dto.RegisterRequest;
import com.mvp.auth_service.application.dto.UserRequestDTO;
import com.mvp.auth_service.application.dto.UserResponseDTO;
import com.mvp.auth_service.application.port.out.UserClientPort;
import com.mvp.auth_service.application.service.AuthService;
import com.mvp.auth_service.infrastructure.mapper.UserMapper;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserClientPort userClientPort;

    public AuthController(AuthService authService, UserClientPort userClientPort) {
        this.authService = authService;
        this.userClientPort = userClientPort;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        try {
            UserRequestDTO userRequestDTO = UserMapper.toUserRequestDTO(request);
            UserResponseDTO userResponseDTO = userClientPort.createUser(userRequestDTO);
            // verificar que userResponseDTO no es nulo y tiene un ID válido
            if (userResponseDTO == null || userResponseDTO.id() == null) {
                return ResponseEntity.status(500).body("Error creating user in user service");
            }
            return ResponseEntity.ok(authService.register(request, userResponseDTO.id()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (RuntimeException e) {
            // Si la contraseña es inválida o el usuario no existe, responde 401
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping()
    public ResponseEntity<String> getMethodName() {
        return ResponseEntity.ok("Hello from GET /path");
    }

}
