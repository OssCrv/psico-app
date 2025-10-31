package com.psicotaller.psicoapp.backend.web.security.jwt;

import com.psicotaller.psicoapp.backend.domain.impl.UserAppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {

    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private UserAppServiceImpl userService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    )
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            String username = jwtManager.extractUsername(jwt);

            if (
                    username != null &&
                            SecurityContextHolder.getContext().getAuthentication() == null
            ) {
                UserDetails user = userService.loadUserByUsername(username);

                if (jwtManager.validateToken(jwt, user)) {
                    String role = jwtManager.extractRole(jwt);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            role != null && !role.isEmpty()
                                    ? Collections.singletonList(new SimpleGrantedAuthority(role))
                                    : user.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
