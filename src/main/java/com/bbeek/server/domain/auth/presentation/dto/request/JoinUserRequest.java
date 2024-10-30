package com.bbeek.server.domain.auth.presentation.dto.request;

import java.util.List;

public record JoinUserRequest(
        String userId,
        String password,
        String username,
        String vegetarianType,
        Integer age,
        String gender,
        Integer height,
        Integer weight,
        List<String> allergy,
        List<String> halal
) {}
