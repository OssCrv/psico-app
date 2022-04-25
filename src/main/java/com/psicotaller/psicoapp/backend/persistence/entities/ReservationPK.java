package com.psicotaller.psicoapp.backend.persistence.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ReservationPK implements Serializable {
    @Column(name = "fk_facility")
    private Integer fkFacility;

    @Column(name = "fk_psicologo")
    private Integer fkPsicologo;

}
