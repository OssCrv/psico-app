package com.psicotaller.psicoapp.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String role;

    @NotBlank
    private String docType;

    private String firstName;

    private String lastName;

    private String document;

    private Boolean professionalCard;

    private String telephone;

    private Boolean isActive;
}
