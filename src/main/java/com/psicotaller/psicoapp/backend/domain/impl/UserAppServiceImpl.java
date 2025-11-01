package com.psicotaller.psicoapp.backend.domain.impl;

import com.psicotaller.psicoapp.backend.domain.dto.UserCreationRequest;
import com.psicotaller.psicoapp.backend.domain.dto.UserDto;
import com.psicotaller.psicoapp.backend.domain.exception.ResourceNotFoundException;
import com.psicotaller.psicoapp.backend.domain.mapper.UserMapper;
import com.psicotaller.psicoapp.backend.persistence.Role;
import com.psicotaller.psicoapp.backend.persistence.UserApp;
import com.psicotaller.psicoapp.backend.persistence.jpa.UserAppJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserAppServiceImpl implements UserDetailsService {

    private static final Set<String> ALLOWED_CREATION_ROLES = Set.of(
            Role.USUARIO,
            Role.TERAPEUTA,
            Role.PACIENTE
    );

    private final UserAppJpaRepository userAppJpaRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public UserApp getByUsername(String usernameApp){
        log.info("Encontrando {}", usernameApp);
        return userAppJpaRepository
                .findByUsername(usernameApp)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Username %s not found", usernameApp)
                ));
    }

    @Transactional(readOnly = true)
    public List<UserDto> listAll() {
        return userMapper.toDto(userAppJpaRepository.findAll());
    }

    @Transactional(readOnly = true)
    public UserDto findOne(Integer id) {
        UserApp user = userAppJpaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id.toString()));
        return userMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserDto> listByRol(String rol) {
        String normalizedRole = normalizeRole(rol);
        return userMapper.toDto(userAppJpaRepository.findAllByRol(normalizedRole));
    }

    public UserDto createUser(UserCreationRequest request) {
        String normalizedRole = normalizeRole(request.getRole());
        validateRoleForCreation(normalizedRole);

        userAppJpaRepository.findByUsername(request.getUsername()).ifPresent(existing -> {
            throw new IllegalArgumentException("Ya existe un usuario con el nombre de usuario proporcionado");
        });

        UserApp user = new UserApp();
        user.setUsername(request.getUsername());
        user.setPass(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRol(normalizedRole);
        user.setDocType(request.getDocType());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDocument(request.getDocument());
        user.setProfessionalCard(request.getProfessionalCard());
        user.setTelephone(request.getTelephone());
        user.setIsActive(Optional.ofNullable(request.getIsActive()).orElse(Boolean.TRUE));

        user = userAppJpaRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserDto updateUser(Integer id, UserDto dto) {
        UserApp user = userAppJpaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id.toString()));

        if (StringUtils.hasText(dto.getUsername())) {
            user.setUsername(dto.getUsername());
        }
        if (StringUtils.hasText(dto.getFirstName())) {
            user.setFirstName(dto.getFirstName());
        }
        if (StringUtils.hasText(dto.getLastName())) {
            user.setLastName(dto.getLastName());
        }
        if (StringUtils.hasText(dto.getDocType())) {
            user.setDocType(dto.getDocType());
        }
        if (StringUtils.hasText(dto.getDocument())) {
            user.setDocument(dto.getDocument());
        }
        if (dto.getProfessionalCard() != null) {
            user.setProfessionalCard(dto.getProfessionalCard());
        }
        if (StringUtils.hasText(dto.getEmail())) {
            user.setEmail(dto.getEmail());
        }
        if (StringUtils.hasText(dto.getTelephone())) {
            user.setTelephone(dto.getTelephone());
        }
        if (dto.getIsActive() != null) {
            user.setIsActive(dto.getIsActive());
        }
        if (StringUtils.hasText(dto.getRole())) {
            String normalizedRole = normalizeRole(dto.getRole());
            validateRoleForCreation(normalizedRole);
            user.setRol(normalizedRole);
        }

        user = userAppJpaRepository.save(user);
        return userMapper.toDto(user);
    }

    public void deleteUser(Integer id) {
        if (!userAppJpaRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", "id", id.toString());
        }
        userAppJpaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.empty();
        Optional<UserApp> userApp =  userAppJpaRepository.findByUsername(username);

        log.info("Cargando usuario {}", username);
        if(userApp.isPresent()){
            user = userApp.map(
                    userApp1 ->
                            new User(
                                    userApp1.getUsername(),
                                    userApp1.getPass(),
                                    Set.of(mapRol(userApp1.getRol())))
            );
        }

        return user.orElseThrow(
                () ->
                        new UsernameNotFoundException(
                                String.format("Username %s not found", username
                        )
                )
        );
    }

    private Role mapRol(String rol) {
        return new Role(normalizeRole(rol));
    }

    private String normalizeRole(String rol) {
        if (!StringUtils.hasText(rol)) {
            throw new IllegalArgumentException("El rol del usuario no puede ser nulo o vacío");
        }

        String normalizedRole = rol.trim().toUpperCase(Locale.ROOT);
        switch (normalizedRole) {
            case Role.ADMIN:
            case Role.USUARIO:
            case Role.TERAPEUTA:
            case Role.PACIENTE:
                return normalizedRole;
            default:
                throw new IllegalArgumentException("Rol desconocido: " + rol);
        }
    }

    private void validateRoleForCreation(String normalizedRole) {
        if (!ALLOWED_CREATION_ROLES.contains(normalizedRole)) {
            throw new IllegalArgumentException("No está permitido crear usuarios con el rol especificado");
        }
    }
}
