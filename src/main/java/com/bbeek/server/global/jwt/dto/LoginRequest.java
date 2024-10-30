package com.bbeek.server.global.jwt.dto;

public record LoginRequest(
        String userId,
        String password
) {}
