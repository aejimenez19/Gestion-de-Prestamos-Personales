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

    @Operation(summary = "Obtener todos los prestamos por prestador del usuario",
            description = "Obtiene una lista de todos los préstamos que el cliente ha tenido con un prestador específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa",
                    content = @Content(schema = @Schema(implementation = ClientDtoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    @GetMapping("/findLoansByProvider")
    public ResponseEntity<List<LoanDtoResponse>> findAllLoanWithProvider(
            @RequestParam Long providerId,
            @RequestHeader("X-User-Id") Long clientId
    ) {
        List<LoanDtoResponse> loans = loanUserCase.findAllLoansByProviderId(clientId, providerId);
        return ResponseEntity.status(HttpStatus.OK).body(loans);
    }

    //Obtener historial de prestamos por proveedor
    @Operation(summary = "Obtener el detalle de un préstamo específico",
            description = "Obtiene el detalle de un préstamo específico que el cliente ha tenido con un prestador.")
    @GetMapping("/findLoanDetailByLoanId")
    public ResponseEntity<LoanDetailDtoResponse> findLoanDetailByLoanId(
            @RequestParam Long loanId,
            @RequestHeader("X-User-Id") Long clientId
    ) {
        LoanDetailDtoResponse loanDetail = loanUserCase.findLoanById(clientId, loanId);
        return ResponseEntity.status(HttpStatus.OK).body(loanDetail);
    }

}
