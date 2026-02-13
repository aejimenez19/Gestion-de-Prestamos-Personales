package com.aejimenezdev.gestionDePrestamosPersonales.web.controller;

import com.aejimenezdev.gestionDePrestamosPersonales.application.usercase.LoanUserCase;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.LoanDtoRequest;
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
import java.util.UUID;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
@Slf4j
public class LoanController {

    private final LoanUserCase loanUserCase;

    @Operation(summary = "Listar préstamos por cliente", description = "Obtiene todos los préstamos de un usuario dado su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa",
                content = @Content(schema = @Schema(implementation = LoanDtoResponse.class)))
    })
    @GetMapping("/{userId}")
    public ResponseEntity<List<LoanDtoResponse>> getAllLoansByUserId(@PathVariable("userId") Long userId) {
        log.info("Received request to get all loans for user ID: {}", userId);
        List<LoanDtoResponse> loans = loanUserCase.findAllLoansByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(loans);
    }
}
