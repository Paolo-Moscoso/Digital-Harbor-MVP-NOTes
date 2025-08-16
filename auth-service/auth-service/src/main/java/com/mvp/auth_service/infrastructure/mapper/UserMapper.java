package com.mvp.auth_service.infrastructure.mapper;

import com.mvp.auth_service.application.dto.RegisterRequest;
import com.mvp.auth_service.application.dto.UserRequestDTO;

public class UserMapper {

    // DTO RegisterRequest → DTO UserRequestDTO
    public static UserRequestDTO toUserRequestDTO(RegisterRequest request) {
        return new UserRequestDTO(
                request.firstName(),
                request.lastName(),
                request.phoneNumber(),
                request.address(),
                request.roleName());
    }

}
