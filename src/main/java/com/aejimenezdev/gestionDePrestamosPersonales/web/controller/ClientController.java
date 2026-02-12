package com.aejimenezdev.gestionDePrestamosPersonales.web.controller;

import com.aejimenezdev.gestionDePrestamosPersonales.application.usercase.ClientUserCase;
import com.aejimenezdev.gestionDePrestamosPersonales.application.usercase.LoanUserCase;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.ClientDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.ClientDtoResponse;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.LoanDetailDtoResponse;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.LoanDtoResponse;
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
    private final LoanUserCase loanUserCase;


    @Operation(summary = "Obtener todos los prestadores del usuario",
            description = "Obtiene una lista de todos los prestadores con los que el cliente ha tenido préstamos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(schema = @Schema(implementation = ClientDtoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @GetMapping("/findProvider")
    public ResponseEntity<List<ClientDtoResponse>> findAllProviderWithLoan(
            @RequestHeader("X-User-Id") Long clientId
    ){
        List<ClientDtoResponse> providers = clientUserCase.findAllProviderWithLoan(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(providers);
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
