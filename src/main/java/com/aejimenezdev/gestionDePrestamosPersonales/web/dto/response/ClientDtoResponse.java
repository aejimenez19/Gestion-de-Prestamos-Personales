package com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDtoResponse {
    private UUID id;
    private String identificationNumber;
    private String name;
    private String phoneNumber;
}
