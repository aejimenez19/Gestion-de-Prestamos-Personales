package com.aejimenezdev.gestionDePrestamosPersonales.application.usercase;

import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.ClientDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.ClientDtoResponse;

import java.util.List;

public interface ClientUserCase {
    List<ClientDtoResponse> findAllProviderWithLoan(Long clientId);
}
