package com.psicotaller.psicoapp.backend.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Id
    @Column(name = "id_reservation")
    private Integer id;

    @Column(name = "fk_facility")
    private Integer fkFacility;

    @Column(name = "fk_therapist")
    private Integer fkTherapist;

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
    @JsonBackReference
    private Facility facility;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("fkTherapist")
    @JoinColumn(name = "fk_therapist", nullable = false)
    @JsonBackReference
    private UserApp therapist;
}