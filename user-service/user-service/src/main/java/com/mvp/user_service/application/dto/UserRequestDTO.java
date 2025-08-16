package com.mvp.user_service.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class UserRequestDTO {
    // validacion para no permitir carateres nulos o vacios y caracteres especiales

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    // validacion para caracteres especial con lombok
    @Pattern(regexp = "^[a-zA-ZñÑáéíóúÁÉÍÓÚüÜ ]+$", message = "El nombre solo puede contener letras y espacios")
    private String firstName;
    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 100, message = "El apellido no puede exceder 100 caracteres")
    // validacion para caracteres especial con lombok
    @Pattern(regexp = "^[a-zA-ZñÑáéíóúÁÉÍÓÚüÜ ]+$", message = "El apellido solo puede contener letras y espacios")
    private String lastName;
    @NotBlank(message = "El número de teléfono no puede estar vacío")
    private String phoneNumber;
    private String address;
    private String roleName;
}
