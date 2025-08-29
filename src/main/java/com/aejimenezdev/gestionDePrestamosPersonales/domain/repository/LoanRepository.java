package com.aejimenezdev.gestionDePrestamosPersonales.domain.repository;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.LoanModel;

import java.util.List;
import java.util.UUID;

public interface LoanRepository {

    List<LoanModel> findAllLoansByClientId(UUID clientId);
    LoanModel saveLoan(LoanModel loanModel);
    Boolean existsById(UUID id);
}
