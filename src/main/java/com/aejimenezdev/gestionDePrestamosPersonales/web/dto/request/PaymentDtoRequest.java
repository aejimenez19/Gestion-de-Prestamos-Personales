package com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDtoRequest {
    @NotNull(message = "Loan ID cannot be null")
    private Long loanId;
    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private double amount;
    private LocalDate paymentDate;
}
