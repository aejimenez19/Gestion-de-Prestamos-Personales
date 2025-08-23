package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.mapper;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.LoanModel;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.LoanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(target = "payments", ignore = true)
    @Mapping(target = "clientId", ignore = true)
    LoanEntity toEntity(LoanModel loanModel);

    @Mapping(target = "clientId", ignore = true)
    @Mapping(target = "payments", ignore = true)
    LoanModel toModel(LoanEntity loanEntity);
}
