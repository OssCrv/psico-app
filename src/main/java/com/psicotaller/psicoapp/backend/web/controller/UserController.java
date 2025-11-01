package com.psicotaller.psicoapp.backend.web.controller;

import com.psicotaller.psicoapp.backend.domain.dto.UserCreationRequest;
import com.psicotaller.psicoapp.backend.domain.dto.UserDto;
import com.psicotaller.psicoapp.backend.domain.impl.UserAppServiceImpl;
import com.psicotaller.psicoapp.backend.persistence.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {

    private final UserAppServiceImpl userService;

    /**
     * Obtiene la lista de usuarios con rol USUARIO.
     *
     * @return Lista de usuarios con rol USUARIO.
     */
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> listApplicationUsers() {
        return listByRole(Role.USUARIO);
    }

    /**
     * Recupera usuarios filtrados por rol TERAPEUTA.
     *
     * @return Lista de usuarios asociados al rol TERAPEUTA.
     */
    @GetMapping("/therapists")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> listTherapists() {
        return listByRole(Role.TERAPEUTA);
    }

    /**
     * Recupera usuarios filtrados por rol ADMIN.
     *
     * @return Lista de usuarios asociados al rol ADMIN.
     */
    @GetMapping("/admins")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> listAdmins() {
        return listByRole(Role.ADMIN);
    }

    /**
     * Recupera usuarios filtrados por rol PACIENTE.
     *
     * @return Lista de usuarios asociados al rol PACIENTE.
     */
    @GetMapping("/patients")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> listPatients() {
        return listByRole(Role.PACIENTE);
    }

    /**
     * Obtiene todos los usuarios registrados, sin distinción de rol.
     *
     * @return Lista completa de usuarios.
     */
    @GetMapping("/users/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> listAllUsers() {
        return userService.listAll();
    }

    /**
     * Busca un usuario específico mediante su identificador.
     *
     * @param id identificador del usuario en la base de datos.
     * @return Usuario encontrado.
     */
    @GetMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDto getUser(@PathVariable Integer id) {
        return userService.findOne(id);
    }

    /**
     * Crea un nuevo usuario del sistema. Solo se permiten los roles USUARIO, TERAPEUTA y PACIENTE.
     *
     * @param request datos necesarios para la creación del usuario.
     * @return Usuario creado sin exponer la contraseña.
     */
    @PostMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreationRequest request) {
        try {
            UserDto created = userService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    /**
     * Actualiza los datos del usuario identificado por el parámetro de ruta. No se expone la contraseña.
     *
     * @param id  identificador del usuario a actualizar.
     * @param dto datos a actualizar.
     * @return Usuario actualizado.
     */
    @PutMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDto updateUser(@PathVariable Integer id, @RequestBody UserDto dto) {
        try {
            return userService.updateUser(id, dto);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    /**
     * Elimina al usuario identificado en la ruta.
     *
     * @param id identificador del usuario a eliminar.
     */
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    private List<UserDto> listByRole(String role) {
        try {
            return userService.listByRol(role);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }
}
