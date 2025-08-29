package com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDtoResponse {
    private UUID id;
    private UUID loanId;
    private double amount;
    private LocalDate paymentDate;

}
