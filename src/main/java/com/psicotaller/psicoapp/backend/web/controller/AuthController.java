package com.psicotaller.psicoapp.backend.web.controller;

import com.psicotaller.psicoapp.backend.domain.impl.UserAppServiceImpl;
import com.psicotaller.psicoapp.backend.domain.dto.AuthenticationRequest;
import com.psicotaller.psicoapp.backend.domain.dto.AuthenticationResponse;
import com.psicotaller.psicoapp.backend.web.security.jwt.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAppServiceImpl userService;

    @Autowired
    private JwtManager jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createToken(
            @RequestBody AuthenticationRequest request
    ) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            UserDetails userDetails = userService.loadUserByUsername(
                    request.getUsername()
            );
            String jwt = jwtUtil.generateToken(userDetails);

            return new ResponseEntity<>(
                    new AuthenticationResponse(jwt),
                    HttpStatus.OK
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    /**@PostMapping("/create")
    public String create(@RequestBody UserCreationRequest userDetails) {
        return userService.save(userDetails);
    }

    @GetMapping("/")
    public List<Object> getAllUsers() {
        return userService.getUsers();
    }
    */
}

