package com.psicotaller.psicoapp.backend.domain.dto;

import lombok.Value;

@Value
public class UserCreationRequest {
    private final String username;
    private final String password;
    private final String email;
    private final String role;
}
