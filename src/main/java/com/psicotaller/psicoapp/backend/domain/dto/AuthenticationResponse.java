package com.psicotaller.psicoapp.backend.domain.dto;

import lombok.Value;

@Value
public class AuthenticationResponse {

    private final String jwt;
    private final String role;
}
