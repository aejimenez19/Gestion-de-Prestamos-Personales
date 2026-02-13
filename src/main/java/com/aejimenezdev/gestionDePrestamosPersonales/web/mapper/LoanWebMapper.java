package com.aejimenezdev.gestionDePrestamosPersonales.web.mapper;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.LoanModel;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.LoanDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.LoanDetailDtoResponse;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.LoanDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanWebMapper {
    @Mapping(target = "providerId.id", source = "providerId")
    LoanModel toModel(LoanDtoRequest loanDtoRequest, Long providerId);

    LoanDtoResponse toDtoResponse(LoanModel loanModel);

    LoanDetailDtoResponse toDetailDtoResponse(LoanModel loanModel);
}
