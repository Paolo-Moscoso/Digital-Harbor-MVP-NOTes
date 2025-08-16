package com.mvp.user_service.domain.model;

import java.util.UUID;

//Modelo de usuario 
public record UserModel(
        UUID id,
        String firstName,
        String lastName,
        String phoneNumber,
        String address,
        Boolean active,
        UUID roleId) {
}
