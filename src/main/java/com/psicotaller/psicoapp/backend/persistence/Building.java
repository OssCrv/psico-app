package com.psicotaller.psicoapp.backend.persistence;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "buildings")
@Data
@ToString
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_building", nullable = false)
    private Integer id;

    @Column(name = "owners_name", length = 100)
    private String ownersName;

    @Column(name = "buildings_address", length = 200)
    private String buildingsAddress;

    @Column(name = "buildings_name", length = 100)
    private String buildingsName;

    @OneToMany(mappedBy = "building")
    @JsonIgnoreProperties(value = { "reservations", "building"}, allowSetters = true)
    private Set<Facility> facilities = new HashSet<>();
}