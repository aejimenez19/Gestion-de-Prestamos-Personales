package com.aejimenezdev.gestionDePrestamosPersonales.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aejimenezdev.gestionDePrestamosPersonales.application.service.PaymentService;
import com.aejimenezdev.gestionDePrestamosPersonales.application.usercase.PaymentUserCase;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.PaymentDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.PaymentDtoResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentUserCase paymentUserCase;

    @PostMapping
    public ResponseEntity<PaymentDtoResponse> createPayment(@RequestBody PaymentDtoRequest paymentDtoRequest) {
        log.info("Received request to create payment: {}", paymentDtoRequest);
        PaymentDtoResponse paymentDtoResponse = paymentUserCase.savePayment(paymentDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentDtoResponse);
    }

    @GetMapping("{loanId}")
    public ResponseEntity<List<PaymentDtoResponse>> getPaymentsByLoanId(@PathVariable("loanId") UUID loanId) {
        log.info("Received request to get payments for loanId: {}", loanId);
        List<PaymentDtoResponse> payments = paymentUserCase.getPaymentsByLoanId(loanId);
        return ResponseEntity.status(HttpStatus.OK).body(payments);
    }

}