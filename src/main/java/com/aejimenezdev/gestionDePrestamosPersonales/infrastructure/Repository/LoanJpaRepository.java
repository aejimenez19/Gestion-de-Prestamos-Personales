package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Repository;

import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.ClientEntity;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public interface LoanJpaRepository extends JpaRepository<LoanEntity, Long> {
    List<LoanEntity> findAllByClientId_Id(Long clientId);

    List<LoanEntity> findAllByClientId_IdAndProviderId_Id(Long clientId, Long providerId);
}
