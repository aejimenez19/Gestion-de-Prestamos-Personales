package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Repository;

import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface ClientsJpaRepository extends JpaRepository<ClientEntity, UUID> {
}
