package com.psicotaller.psicoapp.backend.persistence.entities;

import lombok.Value;
import org.springframework.security.core.GrantedAuthority;

@Value
public class Role implements GrantedAuthority {

    public static final String ADMIN = "ADMIN";
    public static final String USUARIO = "USUARIO";
    public static final String PSICOLOGO = "PSICOLOGO";
    public static final String PACIENTE = "PACIENTE";

    private final String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
