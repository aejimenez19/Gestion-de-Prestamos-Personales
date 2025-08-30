package com.aejimenezdev.gestionDePrestamosPersonales.application.usercase;

import java.util.List;
import java.util.UUID;

import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.PaymentDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.PaymentDtoResponse;

public interface PaymentUserCase {
    PaymentDtoResponse savePayment(PaymentDtoRequest paymentDtoRequest);
    List<PaymentDtoResponse> getPaymentsByLoanId(UUID loanId);
}
