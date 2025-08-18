package com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDtoRequest {
    @NotBlank(message = "Identification number is required")
    private String identificationNumber;
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

}
