package com.aejimenezdev.gestionDePrestamosPersonales.domain.repository;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.PaymentModel;

public interface PaymentRepository {

    PaymentModel save(PaymentModel paymentModel);
}
