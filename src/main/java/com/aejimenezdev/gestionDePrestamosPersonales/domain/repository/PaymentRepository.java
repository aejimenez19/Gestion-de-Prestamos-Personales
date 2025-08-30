package com.aejimenezdev.gestionDePrestamosPersonales.domain.repository;

import java.util.List;
import java.util.UUID;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.PaymentModel;

public interface PaymentRepository {

    PaymentModel save(PaymentModel paymentModel);

    List<PaymentModel> findByLoanId(UUID loanId);
}
