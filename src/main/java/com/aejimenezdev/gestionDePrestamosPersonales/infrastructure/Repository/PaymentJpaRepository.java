package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.PaymentEntity;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID>{

}
