package com.psicotaller.psicoapp.backend.web.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.time.LocalDate;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JwtManager {

    @Autowired
    private Jwt jwt;

    public String generateToken(UserDetails userDetails) {
        Optional<String> role = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst();

        JwtBuilder builder = Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(
                        java.sql.Date.valueOf(
                                LocalDate.now().plusDays(jwt.getTokenExpirationAfterDays())
                        )
                );

        role.ifPresent(value -> builder.claim("role", value));

        return builder
                .signWith(SignatureAlgorithm.HS256, jwt.getSecretKey())
                .compact();
    }

    public boolean validateToken(String token, UserDetails user) {
        return (
                user.getUsername().equals(extractUsername(token)) &&
                        !isTokenExpired(token)
        );
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    public Claims getClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(jwt.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }
}

