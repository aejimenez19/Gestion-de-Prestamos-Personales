package com.aejimenezdev.gestionDePrestamosPersonales.web.controller;

import com.aejimenezdev.gestionDePrestamosPersonales.application.usercase.LoanUserCase;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.LoanDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.LoanDtoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedLoan);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<LoanDtoResponse>> getAllLoansByUserId(@PathVariable("userId") UUID userId) {
        log.info("Received request to get all loans for user ID: {}", userId);
        List<LoanDtoResponse> loans = loanUserCase.findAllLoansByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(loans);
    }
}
