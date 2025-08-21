package com.aejimenezdev.gestionDePrestamosPersonales.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanModel {
    private UUID id;
    private UUID clientId;
    private String monthlyInterestRate;
    private Double amount;
    private LocalDate startDate;
    private List<paymentModel>  payments;
}
