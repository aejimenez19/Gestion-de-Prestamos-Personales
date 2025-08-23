package com.aejimenezdev.gestionDePrestamosPersonales.domain.repository;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.ClientModel;

import java.util.List;
import java.util.UUID;

public interface ClientRepository {
    ClientModel save(ClientModel clientModel);
    Boolean existsByIdentificationNumber(String identificationNumber);
    Boolean existsById(UUID id);
    List<ClientModel> findAllClient();
}
