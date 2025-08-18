package com.aejimenezdev.gestionDePrestamosPersonales.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientModel {
    private UUID id;
    private String identificationNumber;
    private String name;
    private String email;
    private String phoneNumber;

}
