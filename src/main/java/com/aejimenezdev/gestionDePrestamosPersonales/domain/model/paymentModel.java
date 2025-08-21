package com.aejimenezdev.gestionDePrestamosPersonales.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class paymentModel {
    private UUID id;
    private UUID loanId;
    private String amount;
    private LocalDate paymentDate;
}
