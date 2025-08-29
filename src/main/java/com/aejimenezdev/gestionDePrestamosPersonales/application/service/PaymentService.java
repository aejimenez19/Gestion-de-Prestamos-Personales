package com.aejimenezdev.gestionDePrestamosPersonales.application.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.aejimenezdev.gestionDePrestamosPersonales.application.usercase.PaymentUserCase;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.repository.LoanRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.repository.PaymentRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions.ClientExitException;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.PaymentDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.PaymentDtoResponse;
import com.aejimenezdev.gestionDePrestamosPersonales.web.mapper.PaymentWebMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService implements PaymentUserCase {

    private final PaymentRepository paymentRepository;
    private final PaymentWebMapper paymentWebMapper;
    private final LoanRepository loanRepository;

    @Override
    public PaymentDtoResponse savePayment(PaymentDtoRequest paymentDtoRequest) {
        log.info("starting payment save: {}", paymentDtoRequest);
        if (!loanRepository.existsById(paymentDtoRequest.getLoanId())) {
            log.error("the loan with the identification number: {} not exists", paymentDtoRequest.getLoanId());
            throw new ClientExitException("the loan not exists with the provided ID number");
        }

        if (paymentDtoRequest.getPaymentDate() == null || paymentDtoRequest.getPaymentDate().toString().isBlank()) {
            LocalDate localDate = LocalDate.now();
            paymentDtoRequest.setPaymentDate(localDate);
        }
        
        return paymentWebMapper.toDto(paymentRepository.save(paymentWebMapper.toModel(paymentDtoRequest)));
    }
}
