package com.aejimenezdev.gestionDePrestamosPersonales.domain.repository;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.LoanModel;

import java.util.List;
import java.util.UUID;

public interface LoanRepository {

    List<LoanModel> findAllLoansByClientId(Long clientId);
    LoanModel saveLoan(LoanModel loanModel);
    Boolean existsById(Long id);
    LoanModel findById(Long id);

    List<LoanModel> findAllLoansByClientIdAndProviderId(Long clientId, Long providerId);
}
