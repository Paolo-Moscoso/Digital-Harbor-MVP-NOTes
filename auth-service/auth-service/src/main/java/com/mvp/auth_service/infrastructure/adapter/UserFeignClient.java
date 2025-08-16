package com.mvp.auth_service.infrastructure.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mvp.auth_service.application.dto.UserRequestDTO;
import com.mvp.auth_service.application.dto.UserResponseDTO;
import com.mvp.auth_service.infrastructure.config.FeignGlobalConfig;

@FeignClient(name = "user-service", configuration = FeignGlobalConfig.class)
public interface UserFeignClient {

    @PostMapping("/api/users")
    UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO);

}