package com.aejimenezdev.gestionDePrestamosPersonales.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.PaymentModel;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.PaymentDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.PaymentDtoResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentWebMapper {
    
    PaymentDtoResponse toDto(PaymentModel paymentModel);
    PaymentModel toModel(PaymentDtoRequest paymentDtoRequest);

}
