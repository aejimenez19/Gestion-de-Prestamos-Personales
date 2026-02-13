package com.aejimenezdev.gestionDePrestamosPersonales.application.usercase;

import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.LoanDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.LoanDetailDtoResponse;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.LoanDtoResponse;

import java.util.List;
import java.util.UUID;

public interface LoanUserCase {

    LoanDtoResponse saveLoan(LoanDtoRequest loanDtoRequest, Long providerId);
    List<LoanDtoResponse> findAllLoansByUserId(Long userId);
    List<LoanDtoResponse> findAllLoansByProviderId(Long clientId, Long providerId);
    LoanDetailDtoResponse findLoanById(Long clientId, Long loanId);
}
