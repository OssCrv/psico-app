package com.psicotaller.psicoapp.backend.persistence.jpa;

import com.psicotaller.psicoapp.backend.persistence.entities.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityJpaRepository extends JpaRepository<Facility, Integer> {
}