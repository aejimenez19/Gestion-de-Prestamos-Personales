package com.aejimenezdev.gestionDePrestamosPersonales.web.mapper;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.LoanModel;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.LoanDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.LoanDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanWebMapper {
    LoanModel toModel(LoanDtoRequest loanDtoRequest);

    LoanDtoResponse toDtoResponse(LoanModel loanModel);
}
