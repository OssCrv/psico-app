package com.psicotaller.psicoapp.backend.domain.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class TherapistDto implements Serializable {
    private Integer id;
    private String username;
    private String firstName;
    private String docType;
    private String document;
    private Boolean professionalCard;
    private String email;
    private String telephone;
    private Boolean isActive;
}
