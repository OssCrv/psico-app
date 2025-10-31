package com.psicotaller.psicoapp.backend.web.security.jwt;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;

class JwtManagerTest {

    private JwtManager jwtManager;

    @BeforeEach
    void setUp() {
        Jwt jwtConfig = new Jwt();
        jwtConfig.setSecretKey("test-secret-key-value-test-secret-key-value");
        jwtConfig.setTokenExpirationAfterDays(1);
        jwtManager = new JwtManager(jwtConfig);
    }

    @Test
    void generateTokenIncludesRoleClaim() {
        UserDetails userDetails = User
                .withUsername("user")
                .password("password")
                .authorities("ROLE_USER")
                .build();

        String token = jwtManager.generateToken(userDetails);
        Claims claims = jwtManager.getClaims(token);

        assertThat(claims.get("role", String.class)).isEqualTo("ROLE_USER");
    }

    @Test
    void extractRoleReturnsClaimValue() {
        UserDetails userDetails = User
                .withUsername("user")
                .password("password")
                .authorities("ROLE_ADMIN")
                .build();

        String token = jwtManager.generateToken(userDetails);

        assertThat(jwtManager.extractRole(token)).isEqualTo("ROLE_ADMIN");
    }
}
