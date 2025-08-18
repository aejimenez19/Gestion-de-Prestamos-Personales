package com.aejimenezdev.gestionDePrestamosPersonales.application.usercase;

import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.ClientDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.ClientDtoResponse;

public interface ClientUserCase {
    ClientDtoResponse saveClient(ClientDtoRequest clientDtoRequest);
}
