package com.psicotaller.psicoapp.backend.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;

@Entity
@Table(name = "reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @EmbeddedId
    private ReservationPK id;

    @Column(name = "reservation_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date reservationDate;

    @Column(name = "date_creation", nullable = false)
    private Instant dateCreation;

    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("fkFacility")
    @JoinColumn(name = "fk_facility", nullable = false)
    private Facility facility;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("fkPsicologo")
    @JoinColumn(name = "fk_psicologo", nullable = false)
    private UserApp psicologo;
}