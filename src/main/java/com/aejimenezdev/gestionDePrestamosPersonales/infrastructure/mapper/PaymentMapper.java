package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.PaymentModel;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.LoanEntity;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.PaymentEntity;

@Mapper(componentModel = "spring")
public interface PaymentMapper {


    PaymentEntity toEntity(PaymentModel paymentModel);

    @Mapping(target = "loanId", source = "loanId.id")
    PaymentModel toModel(PaymentEntity paymentEntity);

    default LoanEntity map(Long loanId) {
        if (loanId == null) {
            return null;
        }
        LoanEntity loan = new LoanEntity();
        loan.setId(loanId);
        return loan;
    }
}
