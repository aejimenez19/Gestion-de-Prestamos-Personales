package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "identification_number", nullable = false, unique = true)
    private String identificationNumber;
    private String name;
    private String email;
    private String phoneNumber;
    @OneToMany(mappedBy = "clientId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<LoanEntity> loans = new java.util.ArrayList<>();
}
