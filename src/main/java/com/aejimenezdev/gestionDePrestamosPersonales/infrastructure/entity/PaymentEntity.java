package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    @JsonBackReference
    private LoanEntity loanId;
    private Double amount;
    private LocalDate paymentDate;
}
