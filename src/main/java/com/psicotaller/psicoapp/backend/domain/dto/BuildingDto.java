package com.psicotaller.psicoapp.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BuildingDto {
    private Integer id;
    private String ownersName;
    private String buildingsAddress;
    private String buildingsName;
}
