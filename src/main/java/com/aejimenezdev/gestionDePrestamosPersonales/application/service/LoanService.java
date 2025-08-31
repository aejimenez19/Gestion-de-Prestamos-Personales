
package com.aejimenezdev.gestionDePrestamosPersonales.application.service;

import com.aejimenezdev.gestionDePrestamosPersonales.application.usercase.LoanUserCase;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.LoanModel;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.PaymentModel;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.repository.ClientRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.repository.LoanRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions.ClientExitException;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.LoanEntity;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.LoanDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.LoanDtoResponse;
import com.aejimenezdev.gestionDePrestamosPersonales.web.mapper.LoanWebMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanService implements LoanUserCase {

    private final ClientRepository clientRepository;
    private final LoanRepository loanRepository;
    private final LoanWebMapper loanWebMapper;

    @Override
    public LoanDtoResponse saveLoan(LoanDtoRequest loanDtoRequest) {
        log.info("Starting loan save: {}", loanDtoRequest);
        if (!clientRepository.existsById(loanDtoRequest.getClientId())) {
            log.warn("The client with the identification number {} not exists", loanDtoRequest.getClientId());
            throw new ClientExitException("The client not exists with the provided ID number");
        }

        if (loanDtoRequest.getStartDate() == null || loanDtoRequest.getStartDate().toString().isBlank()) {
            LocalDate localDate = LocalDate.now();
            loanDtoRequest.setStartDate(localDate);
        }

        LoanModel loanModel = loanRepository.saveLoan(loanWebMapper.toModel(loanDtoRequest));
        return loanWebMapper.toDtoResponse(loanModel);
    }

    @Override
    public List<LoanDtoResponse> findAllLoansByUserId(UUID userId) {
        log.info("Finding all loans by user ID: {}", userId);
        if (!clientRepository.existsById(userId)) {
            log.warn("The client with the identification number {} not exists", userId);
            throw new ClientExitException("The client not exists with the provided ID number");
        }

        List<LoanModel> loanModels = loanRepository.findAllLoansByClientId(userId);
        return loanModels.stream().map(loanModel -> buildLoanDtoResponse(loanModel)).toList();
    }

    private LoanDtoResponse buildLoanDtoResponse(LoanModel loanModel) {
        if (loanModel == null) {
            return null;
        }
        int elapsedMonths = calculateElapsedMonths(loanModel.getStartDate());
        LoanDtoResponse loanDtoResponse = LoanDtoResponse.builder()
                .id(loanModel.getId())
                .clientId(loanModel.getClientId())
                .amount(loanModel.getAmount())
                .monthlyInterestRate(loanModel.getMonthlyInterestRate())
                .startDate(loanModel.getStartDate())
                .elapsedMonths(elapsedMonths)
                .build();
        loanDtoResponse = calculationStatus(loanDtoResponse, loanModel, elapsedMonths);
        return loanDtoResponse;
    }

    private int calculateElapsedMonths(LocalDate startDate) {
        log.info("Calculating elapsed months for loan starting on: {}", startDate);
        LocalDate currentDate = LocalDate.now();
        return Period.between(startDate, currentDate).getMonths()
                + Period.between(startDate, currentDate).getYears() * 12;
    }

    private LoanDtoResponse calculationStatus(LoanDtoResponse loanDtoResponse, LoanModel loanModel,
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
        loanDtoResponse.setPerDay(perDay);
        loanDtoResponse.setCurrentMonthInterest(lastMonthInterest);
        loanDtoResponse.setOutstandingBalance(outstandingBalance.setScale(2, java.math.RoundingMode.HALF_UP));
        loanDtoResponse.setPrincipalOutstanding(previousBalance.setScale(2, java.math.RoundingMode.HALF_UP));
        loanDtoResponse.setTotalPaidAmount(totalPaymentsMonth.setScale(2, java.math.RoundingMode.HALF_UP));
        return loanDtoResponse;
    }

    private BigDecimal getPaymentsOfMonth(List<PaymentModel> payments, LocalDate monthStart, LocalDate nextMonthStart) {
        return payments.stream()
                .filter(p -> !p.getPaymentDate().isBefore(monthStart) && p.getPaymentDate().isBefore(nextMonthStart))
                .map(p -> BigDecimal.valueOf(p.getAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
