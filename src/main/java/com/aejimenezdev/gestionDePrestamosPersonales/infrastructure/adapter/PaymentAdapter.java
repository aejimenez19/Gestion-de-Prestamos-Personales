package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.adapter;

import org.springframework.stereotype.Repository;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.PaymentModel;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.repository.PaymentRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions.SaveException;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Repository.PaymentJpaRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.mapper.PaymentMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PaymentAdapter implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public PaymentModel save(PaymentModel paymentModel) {
        log.info("persisting payment in database: {}", paymentModel);
        try {
            return paymentMapper.toModel(paymentJpaRepository.save(paymentMapper.toEntity(paymentModel)));
        } catch (Exception e) {
            log.error("Error persisting payment in database: {}", paymentModel, e);
            throw new SaveException("Error persisting payment in database: " + e.getMessage(), e);
        }
    }
}
