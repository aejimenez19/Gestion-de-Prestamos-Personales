package com.aejimenezdev.gestionDePrestamosPersonales.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanModel {
    private Long id;
    private Long clientId;
    private ClientModel providerId;
    private Double monthlyInterestRate;
    private Double amount;
    private LocalDate startDate;
    private List<PaymentModel>  payments;
}
