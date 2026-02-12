package com.aejimenezdev.gestionDePrestamosPersonales.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentModel {
    private Long id;
    private Long loanId;
    private Double amount;
    private LocalDate paymentDate;
}
