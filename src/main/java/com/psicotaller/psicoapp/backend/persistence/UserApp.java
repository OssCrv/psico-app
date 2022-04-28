package com.psicotaller.psicoapp.backend.persistence;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "pass", nullable = false)
    private String pass;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Lob
    @Column(name = "doc_type", nullable = false)
    private String docType;

    @Column(name = "document", length = 100)
    private String document;

    @Column(name = "professional_card")
    private Boolean professionalCard;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "telephone", length = 100)
    private String telephone;

    @Column(name = "is_active")
    private Boolean isActive;

    @Lob
    @Column(name = "rol", nullable = false)
    private String rol;

    @OneToMany(mappedBy = "psicologo")
    @JsonManagedReference
    private List<Reservation> reservations;
}