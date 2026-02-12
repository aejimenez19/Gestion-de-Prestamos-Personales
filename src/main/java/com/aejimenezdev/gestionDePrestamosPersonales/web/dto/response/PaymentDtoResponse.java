package com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response;

import java.time.LocalDate;
import java.util.UUID;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.PaymentModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDtoResponse {
    private Long id;
    private Long loanId;
    private double amount;
    private LocalDate paymentDate;

}
