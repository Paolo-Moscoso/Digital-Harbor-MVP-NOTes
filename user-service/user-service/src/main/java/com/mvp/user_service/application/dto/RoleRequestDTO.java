package com.mvp.user_service.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class RoleRequestDTO {

    @NotBlank(message = "El nombre del rol no puede estar vacío")
    @Size(max = 50, message = "El nombre del rol no puede exceder 50 caracteres")
    private String name;

    @NotBlank(message = "La descripción del rol no puede estar vacía")
    @Size(max = 200, message = "La descripción del rol no puede exceder 200 caracteres")
    private String description;
}