
package com.aejimenezdev.gestionDePrestamosPersonales.application.service;

import com.aejimenezdev.gestionDePrestamosPersonales.application.usercase.LoanUserCase;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.LoanModel;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.repository.ClientRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.repository.LoanRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions.ClientExitException;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.LoanDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.LoanDetailDtoResponse;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.LoanDtoResponse;
import com.aejimenezdev.gestionDePrestamosPersonales.web.mapper.LoanWebMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanService implements LoanUserCase {

    private final ClientRepository clientRepository;
    private final LoanRepository loanRepository;
    private final LoanWebMapper loanWebMapper;
    private final LoanCalculator loanCalculator;

    @Override
    public LoanDtoResponse saveLoan(LoanDtoRequest loanDtoRequest, Long providerId) {
        log.info("Starting loan save: {}", loanDtoRequest);
        validateClientExists(loanDtoRequest.getClientId());
        validateClientExists(providerId);

        if (loanDtoRequest.getStartDate() == null) {
            LocalDate localDate = LocalDate.now();
            loanDtoRequest.setStartDate(localDate);
        }
        LoanModel loanModel = loanWebMapper.toModel(loanDtoRequest, providerId);
        return buildLoanDtoResponse(loanRepository.saveLoan(loanModel));
    }

    @Override
    public List<LoanDtoResponse> findAllLoansByUserId(Long userId) {
        log.info("Finding all loans by user ID: {}", userId);
        validateClientExists(userId);

        List<LoanModel> loanModels = loanRepository.findAllLoansByClientId(userId);
        return loanModels.stream().map(this::buildLoanDtoResponse).toList();
    }

    @Override
    public List<LoanDtoResponse> findAllLoansByProviderId(Long clientId, Long providerId) {
        log.info("Finding all loans by provider ID: {} for client ID: {}", providerId, clientId);
        validateClientExists(clientId);
        validateClientExists(providerId);

        List<LoanModel> loanModels = loanRepository.findAllLoansByClientIdAndProviderId(clientId, providerId);
        if (loanModels.isEmpty()) {
            log.info("No loans found for client ID: {} with provider ID: {}", clientId, providerId);
            return List.of();
        }
        return loanModels.stream().map(this::buildLoanDtoResponse).toList();

    }

    @Override
    public LoanDetailDtoResponse findLoanById(Long clientId, Long loanId) {
        log.info("Finding loan details for loan ID: {} and client ID: {}", loanId, clientId);
        LoanModel loanModel = loanRepository.findById(loanId);
        validateClientExists(clientId);

        LoanDetailDtoResponse detailLoan = loanWebMapper.toDetailDtoResponse(loanModel);
        detailLoan.setElapsedMonths(loanCalculator.calculateElapsedMonths(loanModel.getStartDate()));
        loanCalculator.calculationStatus(detailLoan, loanModel, detailLoan.getElapsedMonths());
        return detailLoan;
    }

    private LoanDtoResponse buildLoanDtoResponse(LoanModel loanModel) {
        if (loanModel == null) {
            return null;
        }
        int elapsedMonths = loanCalculator.calculateElapsedMonths(loanModel.getStartDate());
        LoanDtoResponse loanDtoResponse = LoanDtoResponse.builder()
                .id(loanModel.getId())
                .clientId(loanModel.getClientId())
                .amount(loanModel.getAmount())
                .monthlyInterestRate(loanModel.getMonthlyInterestRate())
                .startDate(loanModel.getStartDate())
                .elapsedMonths(elapsedMonths)
                .build();
        loanCalculator.calculationStatus(loanDtoResponse, loanModel, elapsedMonths);
        return loanDtoResponse;
    }

    private void validateClientExists(Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            log.warn("Client {} not found", clientId);
            throw new ClientExitException("The client does not exist");
        }
    }

}
