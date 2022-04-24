package com.psicotaller.psicoapp.backend.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation", nullable = false)
    private Integer id;

    @Column(name = "reservation_date")
    private Instant reservationDate;

    @Column(name = "date_creation", nullable = false)
    private Instant dateCreation;

    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_facility", nullable = false)
    private Facility fkFacility;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_psicologo", nullable = false)
    private UserApp fkPsicologo;
}