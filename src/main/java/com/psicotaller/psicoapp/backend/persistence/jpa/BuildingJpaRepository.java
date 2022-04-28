package com.psicotaller.psicoapp.backend.persistence.jpa;

import com.psicotaller.psicoapp.backend.persistence.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingJpaRepository extends JpaRepository<Building, Integer> {
}