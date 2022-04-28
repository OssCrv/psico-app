package com.psicotaller.psicoapp.backend.persistence.jpa;

import com.psicotaller.psicoapp.backend.persistence.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationJpaRepository extends JpaRepository<Reservation, Integer> {
}