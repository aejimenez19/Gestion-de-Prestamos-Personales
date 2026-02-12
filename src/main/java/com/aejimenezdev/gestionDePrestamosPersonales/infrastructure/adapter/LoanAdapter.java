package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.adapter;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.ClientModel;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.LoanModel;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.PaymentModel;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.repository.LoanRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions.SaveException;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions.findException;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Repository.LoanJpaRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.ClientEntity;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.LoanEntity;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.mapper.LoanMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.lang.module.FindException;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LoanAdapter implements LoanRepository {

    private final LoanJpaRepository loanJpaRepository;
    private final LoanMapper loanMapper;

    @Override
    public List<LoanModel> findAllLoansByClientId(Long clientId) {
        log.info("fetching loans from database for clientId: {}", clientId);
        try {
            List<LoanEntity> loanEntities = loanJpaRepository.findAllByClientId_Id(clientId);
            return loanEntities.stream().map(LoanEntity -> buildLoanModel(LoanEntity)).toList();
        } catch (Exception e) {
            log.error("Error fetching loans from database for clientId: {}", clientId, e);
            throw new findException("Error fetching loans from database for clientId: " + e.getMessage(), e);
        }
    }

    @Override
    public LoanModel saveLoan(LoanModel loanModel) {
        try {
            log.info("persisting loan in database: {}", loanModel);
            LoanEntity loanEntity = loanMapper.toEntity(loanModel);
            return loanMapper.toModel(loanJpaRepository.save(loanEntity));
        } catch (Exception e) {
            log.error("Error persisting loan in database: {}", loanModel, e);
            throw new SaveException("Error persisting loan in database: " + e.getMessage(), e);
        }
    }

    @Override
    public Boolean existsById(Long id) {
        try {
            log.info("checking existence of loan in database for id: {}", id);
            return loanJpaRepository.existsById(id);
        } catch (Exception e) {
            log.error("Error checking existence of loan in database for id: {}", id, e);
            throw new findException("Error checking existence of loan in database for id: " + e.getMessage(), e);
        }
    }

    @Override
    public LoanModel findById(Long id) {
        try {
            log.info("checking existence of loan in database for id: {}", id);
            LoanEntity loanEntity = loanJpaRepository.findById(id).orElseThrow(() -> new FindException("Loan not found with id: " + id));
            return buildLoanModel(loanEntity);
        } catch (Exception e) {
            log.error("Error checking existence of loan in database for id: {}", id, e);
            throw new findException("Error checking existence of loan in database for id: " + e.getMessage(), e);
        }
    }

    @Override
    public List<LoanModel> findAllLoansByClientIdAndProviderId(Long clientId, Long providerId) {
        try {
            log.info("fetching loans from database for clientId: {} and providerId: {}", clientId, providerId);

            return loanJpaRepository.findAllByClientId_IdAndProviderId_Id(clientId, providerId)
                    .stream()
                    .map(loanEntity -> buildLoanModel(loanEntity))
                    .toList();
        } catch (Exception e) {
            log.error("Error fetching loans from database for clientId: {} and providerId: {}", clientId, providerId, e);
            throw new findException("Error fetching loans from database for clientId: " + clientId + " and providerId: " + providerId + ": " + e.getMessage(), e);
        }
    }

    private LoanModel buildLoanModel(LoanEntity loanEntity) {
        if (loanEntity == null) {
            return null;
        }
        List<PaymentModel> paymentModels = null;
        if (loanEntity.getPayments() != null) {
            paymentModels = loanEntity.getPayments()
                    .stream()
                    .map(paymentEntity -> PaymentModel.builder()
                            .id(paymentEntity.getId())
                            .loanId(paymentEntity.getLoanId().getId())
                            .amount(paymentEntity.getAmount())
                            .paymentDate(paymentEntity.getPaymentDate())
                            .build())
                    .toList();
        }
        ClientModel providerModel = ClientModel.builder()
                .id(loanEntity.getProviderId().getId())
                .name(loanEntity.getProviderId().getName())
                .identifier(loanEntity.getProviderId().getIdentifier())
                .email(loanEntity.getProviderId().getEmail())
                .phone(loanEntity.getProviderId().getPhone())
                .build();
        return LoanModel.builder()
                .id(loanEntity.getId())
                .clientId(loanEntity.getClientId().getId())
                .providerId(providerModel)
                .amount(loanEntity.getAmount())
                .startDate(loanEntity.getStartDate())
                .monthlyInterestRate(loanEntity.getMonthlyInterestRate())
                .payments(paymentModels)
                .build();
    }

}
