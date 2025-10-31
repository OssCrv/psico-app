package com.psicotaller.psicoapp.backend.web.controller;

import com.psicotaller.psicoapp.backend.domain.dto.UserCreationRequest;
import com.psicotaller.psicoapp.backend.domain.dto.UserDto;
import com.psicotaller.psicoapp.backend.domain.impl.UserAppServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {

    private final UserAppServiceImpl userService;

    /**
     * Obtiene la lista completa de usuarios registrados en el sistema.
     *
     * @return Lista de usuarios sin exponer credenciales sensibles.
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> listUsers() {
        return userService.listAll();
    }

    /**
     * Busca un usuario específico mediante su identificador.
     *
     * @param id identificador del usuario en la base de datos.
     * @return Usuario encontrado.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDto getUser(@PathVariable Integer id) {
        return userService.findOne(id);
    }

    /**
     * Recupera usuarios filtrados por rol. Los roles admitidos son ADMIN, USUARIO, TERAPEUTA y PACIENTE.
     *
     * @param rol cadena con el nombre del rol a filtrar.
     * @return Lista de usuarios asociados al rol proporcionado.
     */
    @GetMapping("/role/{rol}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> listByRole(@PathVariable String rol) {
        try {
            return userService.listByRol(rol);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        }
    }

    /**
     * Crea un nuevo usuario del sistema. Solo se permiten los roles USUARIO, TERAPEUTA y PACIENTE.
     *
     * @param request datos necesarios para la creación del usuario.
     * @return Usuario creado sin exponer la contraseña.
     */
    @PostMapping
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
    @PutMapping("/{id}")
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
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
