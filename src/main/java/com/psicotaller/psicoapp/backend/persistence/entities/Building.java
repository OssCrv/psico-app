package com.psicotaller.psicoapp.backend.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "buildings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_building", nullable = false)
    private Integer id;

    @Column(name = "owners_name", length = 100)
    private String ownersName;

    @Column(name = "buildings_adress", length = 200)
    private String buildingsAdress;

    @Column(name = "buildings_name", length = 100)
    private String buildingsName;

    @OneToMany(mappedBy = "building")
    private List<Facility> facilities;
}