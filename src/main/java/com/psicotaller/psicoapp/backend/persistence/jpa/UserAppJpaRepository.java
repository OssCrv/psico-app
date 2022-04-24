package com.psicotaller.psicoapp.backend.persistence.jpa;

import com.psicotaller.psicoapp.backend.persistence.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAppJpaRepository extends JpaRepository<UserApp, Integer> {
    Optional<UserApp> findByUsername(String username);
}
