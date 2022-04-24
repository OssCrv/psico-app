package com.psicotaller.psicoapp.backend.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_building")
    private Building fkBuilding;

    @Column(name = "room_number")
    private String roomNumber;
}