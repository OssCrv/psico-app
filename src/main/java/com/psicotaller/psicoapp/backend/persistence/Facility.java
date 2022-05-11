package com.psicotaller.psicoapp.backend.persistence;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "facilities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facility", nullable = false)
    private Integer id;

    @Column(name = "fk_building")
    private Integer fkBuilding;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_building", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = { "facility"})
    //@JsonBackReference
    private Building building;

    @Column(name = "room_number")
    private String roomNumber;

    @OneToMany(mappedBy = "facility")
    @JsonIgnoreProperties(value = { "facility"})
    private List<Reservation> reservations;
}