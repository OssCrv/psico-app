package com.psicotaller.psicoapp.backend.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psicotaller.psicoapp.backend.domain.dto.UserCreationRequest;
import com.psicotaller.psicoapp.backend.persistence.Role;
import com.psicotaller.psicoapp.backend.persistence.UserApp;
import com.psicotaller.psicoapp.backend.persistence.jpa.UserAppJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserAppJpaRepository userAppJpaRepository;

    @BeforeEach
    void setUp() {
        userAppJpaRepository.deleteAll();
    }

    @ParameterizedTest
    @MethodSource("supportedRoles")
    @DisplayName("debería crear y consultar usuarios por rol permitido")
    @WithMockUser(authorities = Role.ADMIN)
    void shouldCreateAndQueryUsersByRole(String role) throws Exception {
        String username = "usuario-" + role.toLowerCase(Locale.ROOT);
        String email = username + "@example.com";

        UserCreationRequest request = new UserCreationRequest(
                username,
                "ClaveSegura123",
                email,
                role,
                "CC",
                "Nombre",
                "Apellido",
                "123456",
                Boolean.TRUE,
                "3001234567",
                Boolean.TRUE
        );

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value(username))
                .andExpect(jsonPath("$.role").value(role))
                .andExpect(jsonPath("$.pass").doesNotExist());

        mockMvc.perform(get("/api/users/role/" + role.toLowerCase(Locale.ROOT)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value(username))
                .andExpect(jsonPath("$[0].role").value(role))
                .andExpect(jsonPath("$[0].pass").doesNotExist());

        UserApp storedUser = userAppJpaRepository.findByUsername(username).orElseThrow();
        assertThat(storedUser.getRol()).isEqualTo(role);
        assertThat(storedUser.getPass()).isNotEqualTo("ClaveSegura123");
    }

    private static Stream<String> supportedRoles() {
        return Stream.of(Role.USUARIO, Role.TERAPEUTA, Role.PACIENTE);
    }
}
