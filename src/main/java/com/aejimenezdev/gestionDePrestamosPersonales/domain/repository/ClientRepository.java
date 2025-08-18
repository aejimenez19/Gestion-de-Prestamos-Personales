package com.aejimenezdev.gestionDePrestamosPersonales.domain.repository;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.ClientModel;

import java.util.List;

public interface ClientRepository {
    ClientModel save(ClientModel clientModel);
}
