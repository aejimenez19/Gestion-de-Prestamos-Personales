package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.adapter;

import java.lang.module.FindException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.PaymentModel;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.repository.PaymentRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions.SaveException;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Repository.PaymentJpaRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.LoanEntity;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.PaymentEntity;
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

    @Override
    public List<PaymentModel> findByLoanId(UUID loanId) {
        log.info("Retrieving payments for loanId: {}", loanId);
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setId(loanId);
        try {
            List<PaymentEntity> payments = paymentJpaRepository.findByLoanId(loanEntity);
            return payments.stream().map(paymentMapper::toModel).toList();
        } catch (Exception e) {
            log.error("Error retrieving payments for loanId: {}", loanId, e);
            throw new FindException("Error retrieving payments for loanId: " + loanId, e);
        }

    }
}
