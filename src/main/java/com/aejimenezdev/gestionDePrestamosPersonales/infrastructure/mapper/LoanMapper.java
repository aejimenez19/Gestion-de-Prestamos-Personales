package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.mapper;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.LoanModel;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.ClientEntity;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.LoanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(target = "payments", ignore = true)
    LoanEntity toEntity(LoanModel loanModel);

    @Mapping(target = "clientId", source = "clientId.id")
    @Mapping(target = "payments", ignore = true)
    LoanModel toModel(LoanEntity loanEntity);

    default ClientEntity map(Long clientId) {
        if (clientId == null) {
            return null;
        }
        ClientEntity client = new ClientEntity();
        client.setId(clientId);
        return client;
    }
}
