package com.psicotaller.psicoapp.backend.persistence;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_building")
    @JsonBackReference
    private Building building;

    @Column(name = "room_number")
    private String roomNumber;

    @OneToMany(mappedBy = "facility")
    @JsonManagedReference
    private List<Reservation> reservations;
}