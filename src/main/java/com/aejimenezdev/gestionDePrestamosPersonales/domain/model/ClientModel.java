package com.aejimenezdev.gestionDePrestamosPersonales.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientModel {
    private Long id;
    private String identifier;
    private String name;
    private String email;
    private String phone;
    private List<LoanModel> clientLoans;
}
