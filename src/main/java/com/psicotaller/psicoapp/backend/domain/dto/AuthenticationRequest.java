package com.psicotaller.psicoapp.backend.domain.dto;

import lombok.Value;

@Value
public class AuthenticationRequest {

    private final String username;
    private final String password;
}