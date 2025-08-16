package com.mvp.auth_service.application.port.out;

import com.mvp.auth_service.application.dto.UserRequestDTO;
import com.mvp.auth_service.application.dto.UserResponseDTO;

public interface UserClientPort {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
}