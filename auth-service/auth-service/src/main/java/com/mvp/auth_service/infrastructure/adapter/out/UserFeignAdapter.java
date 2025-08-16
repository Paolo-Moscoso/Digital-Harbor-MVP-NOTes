package com.mvp.auth_service.infrastructure.adapter.out;

import org.springframework.stereotype.Component;

import com.mvp.auth_service.application.dto.UserRequestDTO;
import com.mvp.auth_service.application.dto.UserResponseDTO;
import com.mvp.auth_service.application.port.out.UserClientPort;
import com.mvp.auth_service.infrastructure.adapter.UserFeignClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserFeignAdapter implements UserClientPort {

    private final UserFeignClient userFeignClient;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        return userFeignClient.createUser(userRequestDTO);
    }
}