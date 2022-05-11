package com.psicotaller.psicoapp.backend.domain.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class BuildingDto implements Serializable {
    private Integer id;
    private String ownersName;
    private String buildingsAddress;
    private String buildingsName;
}
