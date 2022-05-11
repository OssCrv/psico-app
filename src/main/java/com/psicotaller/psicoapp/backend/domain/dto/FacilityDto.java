package com.psicotaller.psicoapp.backend.domain.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class FacilityDto implements Serializable {
    private Integer id;
    private Integer fkBuilding;
    private BuildingDto buildingDto;
    private String roomNumber;
}
