package com.aejimenezdev.gestionDePrestamosPersonales.web.controller;

import com.aejimenezdev.gestionDePrestamosPersonales.application.usercase.ClientUserCase;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.ClientDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.ClientDtoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientUserCase clientUserCase;

    @PostMapping
    public ResponseEntity<ClientDtoResponse> saveClient(@Valid @RequestBody ClientDtoRequest clientDtoRequest) {
        log.info("Received request to save client: {}", clientDtoRequest);
        ClientDtoResponse savedClient = clientUserCase.saveClient(clientDtoRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(savedClient);
    }

}
