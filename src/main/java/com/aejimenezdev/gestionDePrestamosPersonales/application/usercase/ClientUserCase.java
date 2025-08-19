package com.aejimenezdev.gestionDePrestamosPersonales.application.usercase;

import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.ClientDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.ClientDtoResponse;

import java.util.List;

public interface ClientUserCase {
    ClientDtoResponse saveClient(ClientDtoRequest clientDtoRequest);

    List<ClientDtoResponse> findAllClient();
}
