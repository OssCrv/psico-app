package com.psicotaller.psicoapp.backend.domain.impl;

import com.psicotaller.psicoapp.backend.persistence.Role;
import com.psicotaller.psicoapp.backend.persistence.UserApp;
import com.psicotaller.psicoapp.backend.persistence.jpa.UserAppJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserAppServiceImpl implements UserDetailsService {

    @Autowired
    private UserAppJpaRepository userAppJpaRepository;

    public UserApp getByUsername(String usernameApp){
        log.info("Encontrando ", usernameApp);
        return userAppJpaRepository.findByUsername(usernameApp).orElse(new UserApp());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.empty();
        Optional<UserApp> userApp =  userAppJpaRepository.findByUsername(username);

        log.info("Cargando usuario", username);
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
        if (rol == null) {
            throw new IllegalArgumentException("El rol del usuario no puede ser nulo");
        }

        String normalizedRole = rol.trim().toUpperCase(Locale.ROOT);
        switch (normalizedRole) {
            case Role.ADMIN:
            case Role.USUARIO:
            case Role.TERAPEUTA:
            case Role.PACIENTE:
                return new Role(normalizedRole);
            default:
                throw new IllegalArgumentException("Rol desconocido: " + rol);
        }
    }
}
