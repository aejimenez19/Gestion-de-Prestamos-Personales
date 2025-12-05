package com.aejimenezdev.gestionDePrestamosPersonales.web.controller;

import com.aejimenezdev.gestionDePrestamosPersonales.application.usercase.ClientUserCase;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.ClientDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.ClientDtoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientUserCase clientUserCase;

    @Operation(summary = "Crear un nuevo cliente", description = "Registra un nuevo cliente en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente",
                content = @Content(schema = @Schema(implementation = ClientDtoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ClientDtoResponse> saveClient(@Valid @RequestBody ClientDtoRequest clientDtoRequest) {
        log.info("Received request to save client: {}", clientDtoRequest);
        ClientDtoResponse savedClient = clientUserCase.saveClient(clientDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedClient);
    }

    @Operation(summary = "Listar todos los clientes", description = "Obtiene la lista de todos los clientes registrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
                content = @Content(schema = @Schema(implementation = ClientDtoResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<ClientDtoResponse>> findAllClient(){
        log.info("Request was received to get all clients");
        return ResponseEntity.status(HttpStatus.OK).body(clientUserCase.findAllClient());
    }
}
