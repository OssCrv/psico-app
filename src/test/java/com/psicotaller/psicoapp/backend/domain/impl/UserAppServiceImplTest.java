package com.psicotaller.psicoapp.backend.domain.impl;

import com.psicotaller.psicoapp.backend.domain.mapper.UserMapper;
import com.psicotaller.psicoapp.backend.persistence.Role;
import com.psicotaller.psicoapp.backend.persistence.UserApp;
import com.psicotaller.psicoapp.backend.persistence.jpa.UserAppJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAppServiceImplTest {

    @Mock
    private UserAppJpaRepository userAppJpaRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserAppServiceImpl userAppService;

    @ParameterizedTest
    @MethodSource("supportedRoles")
    void loadUserByUsernameShouldReturnUserWithExpectedRole(String rol) {
        String username = "user-" + rol.toLowerCase();

        UserApp userApp = new UserApp();
        userApp.setUsername(username);
        userApp.setPass("encoded-password");
        userApp.setRol(rol);

        when(userAppJpaRepository.findByUsername(username)).thenReturn(Optional.of(userApp));

        UserDetails userDetails = userAppService.loadUserByUsername(username);

        assertThat(userDetails.getUsername()).isEqualTo(username);
        assertThat(userDetails.getAuthorities()).containsExactly(new Role(rol));
    }

    @Test
    void loadUserByUsernameShouldFailForUnknownRole() {
        String username = "user-desconocido";

        UserApp userApp = new UserApp();
        userApp.setUsername(username);
        userApp.setPass("encoded-password");
        userApp.setRol("DESCONOCIDO");

        when(userAppJpaRepository.findByUsername(username)).thenReturn(Optional.of(userApp));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> userAppService.loadUserByUsername(username));

        assertThat(exception.getMessage()).contains("Rol desconocido");
    }

    private static Stream<String> supportedRoles() {
        return Stream.of(Role.ADMIN, Role.USUARIO, Role.TERAPEUTA, Role.PACIENTE);
    }
}
