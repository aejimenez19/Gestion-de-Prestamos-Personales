package com.aejimenezdev.gestionDePrestamosPersonales.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aejimenezdev.gestionDePrestamosPersonales.application.service.PaymentService;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.PaymentDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.PaymentDtoResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDtoResponse> createPayment(@RequestBody PaymentDtoRequest paymentDtoRequest) {
        log.info("Received request to create payment: {}", paymentDtoRequest);
        PaymentDtoResponse paymentDtoResponse = paymentService.savePayment(paymentDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentDtoResponse);
    }

}
