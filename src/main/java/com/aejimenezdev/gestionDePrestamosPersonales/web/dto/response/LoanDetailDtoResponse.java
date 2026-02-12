package com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class LoanDetailDtoResponse implements LoanSumary{
    private Long id;
    private Long clientId;
    private Double amount;
    private Double monthlyInterestRate;
    private LocalDate startDate;
    private boolean isPerDay;
    private BigDecimal outstandingBalance;
    private BigDecimal currentMonthInterest;
    private BigDecimal principalOutstanding;
    private BigDecimal totalPaidAmount;
    private int elapsedMonths;
    private List<PaymentDtoResponse> payments;
}
