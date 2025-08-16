package com.mvp.auth_service.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;

@Configuration
public class FeignGlobalConfig {
    @Value("${gateway.internal.token}")
    private String gatewayToken;

    // Interceptor para agregar el header X-GATEWAY-TOKEN en cada request Feign
    @Bean
    public RequestInterceptor gatewayTokenInterceptor() {
        return template -> template.header("X-GATEWAY-TOKEN", gatewayToken);
    }
}