package com.aejimenezdev.gestionDePrestamosPersonales.web.controller;

import com.aejimenezdev.gestionDePrestamosPersonales.application.usercase.LoanUserCase;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.LoanDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.LoanDtoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
@Slf4j
public class LoanController {

    private final LoanUserCase loanUserCase;

    @PostMapping
    public ResponseEntity<LoanDtoResponse> saveLoan(@Valid @RequestBody LoanDtoRequest loanDtoRequest) {
        log.info("Received request to save loan: {}", loanDtoRequest);
        LoanDtoResponse savedLoan = loanUserCase.saveLoan(loanDtoRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(savedLoan);
    }
}
