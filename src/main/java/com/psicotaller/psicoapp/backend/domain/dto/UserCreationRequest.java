package com.psicotaller.psicoapp.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequest {

    private String username;

    private String password;

    private String email;

    private String role;

    private String docType;

    private String firstName;

    private String lastName;

    private String document;

    private Boolean professionalCard;

    private String telephone;

    private Boolean isActive;
}
