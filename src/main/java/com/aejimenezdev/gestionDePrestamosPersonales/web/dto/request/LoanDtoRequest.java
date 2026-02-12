package com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDtoRequest {
    @NotNull(message = "Client ID cannot be null")
    private Long clientId;
    @Positive(message = "The amount must be positive")
    @NotNull( message = "Amount cannot be null")
    private Double amount;
    @Min(value = 1, message = "The term must be at least 1 month")
    @Max(value = 100, message = "The term must be at most 100 months")
    private Double monthlyInterestRate;
    private LocalDate startDate;
}
