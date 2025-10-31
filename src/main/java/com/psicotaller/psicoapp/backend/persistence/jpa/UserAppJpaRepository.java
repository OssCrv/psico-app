package com.psicotaller.psicoapp.backend.persistence.jpa;

import com.psicotaller.psicoapp.backend.persistence.UserApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAppJpaRepository extends JpaRepository<UserApp, Integer> {
    Optional<UserApp> findByUsername(String username);

    List<UserApp> findAllByRol(String rol);

    Page<UserApp> findAllByRol(String rol, Pageable pageable);
}
