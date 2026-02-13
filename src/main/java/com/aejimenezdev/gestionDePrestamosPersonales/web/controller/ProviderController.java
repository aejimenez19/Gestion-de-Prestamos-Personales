package com.aejimenezdev.gestionDePrestamosPersonales.web.controller;

import com.aejimenezdev.gestionDePrestamosPersonales.application.usercase.LoanUserCase;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.LoanDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.ClientDtoResponse;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.LoanDtoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/provider")
@RequiredArgsConstructor
public class ProviderController {
    private final LoanUserCase loanUserCase;


    @Operation(summary = "Hacer un préstamo a un cliente",
            description = "Permite a un prestador realizar un préstamo a un cliente especificando los detalles del préstamo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Préstamo creado exitosamente",
                    content = @Content(schema = @Schema(implementation = ClientDtoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @PostMapping("/saveLoan")
    public ResponseEntity<LoanDtoResponse> makeLoanToClient(
            @RequestHeader ("X-User-Id") Long providerId,
            @RequestBody @Valid LoanDtoRequest newLoan
    ) {
        return ResponseEntity.status(201).body(loanUserCase.saveLoan(newLoan, providerId));
    }

    //Obtener lista de clientes con los que tiene prestamo

    //Obtener la lista de prestamos de un cliente

    //Obtener historial de prestamos por cliente

    //Ingresar un pago hecho por un cliente



}
