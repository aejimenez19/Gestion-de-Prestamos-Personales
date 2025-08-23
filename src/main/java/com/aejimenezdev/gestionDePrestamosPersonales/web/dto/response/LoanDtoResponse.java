package com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDtoResponse {
    private UUID id;
    private UUID clientId;
    private Double amount;
    private Double monthlyInterestRate;
    private LocalDate startDate;
}
