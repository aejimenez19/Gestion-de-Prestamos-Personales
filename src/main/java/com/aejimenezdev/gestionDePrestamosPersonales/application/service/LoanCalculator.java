package com.aejimenezdev.gestionDePrestamosPersonales.application.service;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.LoanModel;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.PaymentModel;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.LoanSumary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@Slf4j
public class LoanCalculator {

    public void calculationStatus(LoanSumary response, LoanModel loanModel,
                                   Integer totalPaidMonths) {
        boolean perDay = true;
        BigDecimal lastMonthInterest = BigDecimal.ZERO;
        BigDecimal previousBalance = BigDecimal.ZERO;
        BigDecimal totalPaymentsMonth = BigDecimal.ZERO;
        BigDecimal outstandingBalance = BigDecimal.valueOf(loanModel.getAmount());
        BigDecimal rate = BigDecimal.valueOf(loanModel.getMonthlyInterestRate()).divide(BigDecimal.valueOf(100));

        for (int month = 0; month <= totalPaidMonths; month++) {
            previousBalance = outstandingBalance;
            BigDecimal interestOfMonth = previousBalance.multiply(rate);
            outstandingBalance = outstandingBalance.add(interestOfMonth);
            LocalDate monthStart = loanModel.getStartDate().plusMonths(month);
            LocalDate nextMonthStart = monthStart.plusMonths(1);
            BigDecimal paymentsOfMonth = getPaymentsOfMonth(loanModel.getPayments(), monthStart, nextMonthStart);
            totalPaymentsMonth = totalPaymentsMonth.add(paymentsOfMonth);
            outstandingBalance = outstandingBalance.subtract(paymentsOfMonth);
            if (month == totalPaidMonths && paymentsOfMonth.compareTo(interestOfMonth) < 0) {
                lastMonthInterest = interestOfMonth.subtract(paymentsOfMonth).setScale(2,
                        java.math.RoundingMode.HALF_UP);
                perDay = false;
            }
        }
        response.setPerDay(perDay);
        response.setCurrentMonthInterest(lastMonthInterest);
        response.setOutstandingBalance(outstandingBalance.setScale(2, java.math.RoundingMode.HALF_UP));
        response.setPrincipalOutstanding(previousBalance.setScale(2, java.math.RoundingMode.HALF_UP));
        response.setTotalPaidAmount(totalPaymentsMonth.setScale(2, java.math.RoundingMode.HALF_UP));
    }

    public int calculateElapsedMonths(LocalDate startDate) {
        log.info("Calculating elapsed months for loan starting on: {}", startDate);
        LocalDate currentDate = LocalDate.now();
        return Period.between(startDate, currentDate).getMonths()
                + Period.between(startDate, currentDate).getYears() * 12;
    }

    private BigDecimal getPaymentsOfMonth(List<PaymentModel> payments, LocalDate monthStart, LocalDate nextMonthStart) {
        if (payments == null || payments.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return payments.stream()
                .filter(p -> !p.getPaymentDate().isBefore(monthStart) && p.getPaymentDate().isBefore(nextMonthStart))
                .map(p -> BigDecimal.valueOf(p.getAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
