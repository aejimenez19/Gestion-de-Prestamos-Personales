package com.aejimenezdev.gestionDePrestamosPersonales.application.service;

import com.aejimenezdev.gestionDePrestamosPersonales.application.usercase.ClientUserCase;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.ClientModel;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.repository.ClientRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions.ClientExitException;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions.SaveException;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.ClientDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.ClientDtoResponse;
import com.aejimenezdev.gestionDePrestamosPersonales.web.mapper.ClientWebMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService implements ClientUserCase {

    private final ClientRepository clientRepository;
    private final ClientWebMapper clientWebMapper;

    @Override
    public ClientDtoResponse saveClient(ClientDtoRequest clientDtoRequest) {
        log.info("Starting client save: {}", clientDtoRequest);
        if (clientRepository.existsByIdentificationNumber(clientDtoRequest.getIdentificationNumber())) {
            log.warn("The client with the identification number {} already exists", clientDtoRequest.getIdentificationNumber());
            throw new ClientExitException("The client already exists with the provided ID number");
        }
        try {
            ClientModel clientModel = clientWebMapper.toModel(clientDtoRequest);
            ClientModel savedModel = clientRepository.save(clientModel);
            return clientWebMapper.toDtoResponse(savedModel);
        } catch (Exception e) {
            log.error("Error saving client: {}", clientDtoRequest, e);
            throw new SaveException("Error saving client", e);
        }
    }

    @Override
    public List<ClientDtoResponse> findAllClient() {
        log.info("Starting find clients");
        return clientRepository.findAllClient().stream().map(clientWebMapper::toDtoResponse).toList();
    }
}
