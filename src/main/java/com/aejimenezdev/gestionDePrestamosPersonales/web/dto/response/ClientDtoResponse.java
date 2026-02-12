package com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDtoResponse {
    private Long id;
    private String identifier;
    private String name;
    private String phone;
}
