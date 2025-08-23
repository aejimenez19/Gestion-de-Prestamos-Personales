package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.adapter;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.LoanModel;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.repository.LoanRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions.SaveException;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Repository.LoanJpaRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.LoanEntity;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.mapper.LoanMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class LoanAdapter implements LoanRepository {

    private final LoanJpaRepository loanJpaRepository;
    private final LoanMapper loanMapper;

    @Override
    public List<LoanModel> findAllLoansByClientId(UUID clientId) {
        return List.of();
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
}
