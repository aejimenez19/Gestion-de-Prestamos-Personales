package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.adapter;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.ClientModel;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.repository.ClientRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions.SaveException;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Repository.ClientsJpaRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.ClientEntity;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ClientAdapter implements ClientRepository {
    private final ClientsJpaRepository clientsJpaRepository;
    private final ClientMapper clientMapper;

      @Override
      @Transactional
      public ClientModel save(ClientModel clientModel) {
          try {
              log.info("persisting client in database: {}", clientModel);
              ClientEntity clientEntity = clientMapper.toEntity(clientModel);
              ClientEntity savedEntity = clientsJpaRepository.save(clientEntity);
              return clientMapper.toModel(savedEntity);
          } catch (Exception e) {
              log.error("Error persisting client in database: {}", clientModel, e);
              throw new SaveException("Error persisting client in database: " + e.getMessage(), e);
          }
      }

    @Override
    public Boolean existsByIdentificationNumber(String identificationNumber) {
        log.info("Checking if client exists by identification number: {}", identificationNumber);
        return  clientsJpaRepository.existsByIdentificationNumber(identificationNumber);
    }

    @Override
    public Boolean existsById(UUID id) {
        log.info("Checking if client exists by identification number: {}", id);
        return  clientsJpaRepository.existsById(id);
    }

    @Override
    public List<ClientModel> findAllClient() {
        try {
            log.info("Get all the clients");
            return clientsJpaRepository.findAll().stream().map(clientMapper::toModel).toList();
        } catch (Exception e) {
            log.error("error getting all clients");
            throw new RuntimeException("error getting all clients", e);
        }
    }


}
