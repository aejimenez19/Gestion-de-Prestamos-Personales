package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "loans")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonBackReference
    private ClientEntity clientId;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    @JsonBackReference
    private ClientEntity providerId;

    private Double monthlyInterestRate;

    private Double amount;

    private LocalDate startDate;

    @OneToMany( mappedBy = "loanId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PaymentEntity> payments = new java.util.ArrayList<>();

}
