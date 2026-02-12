package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.LoanEntity;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.PaymentEntity;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, Long>{
    List<PaymentEntity> findByLoanId(LoanEntity loanEntity);

}
