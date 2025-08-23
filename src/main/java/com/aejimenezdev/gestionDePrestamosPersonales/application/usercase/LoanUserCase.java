package com.aejimenezdev.gestionDePrestamosPersonales.application.usercase;

import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.LoanDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.LoanDtoResponse;

public interface LoanUserCase {

    LoanDtoResponse saveLoan(LoanDtoRequest loanDtoRequest);
}
