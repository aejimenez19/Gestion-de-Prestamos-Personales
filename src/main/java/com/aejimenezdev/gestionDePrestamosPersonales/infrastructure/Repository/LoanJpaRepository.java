package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Repository;

import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface LoanJpaRepository extends JpaRepository<LoanEntity, UUID> {
}
