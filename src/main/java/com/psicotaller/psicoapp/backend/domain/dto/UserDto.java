package com.psicotaller.psicoapp.backend.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String docType;
    private String document;
    private Boolean professionalCard;
    private String email;
    private String telephone;
    private Boolean isActive;
    private String role;
}
