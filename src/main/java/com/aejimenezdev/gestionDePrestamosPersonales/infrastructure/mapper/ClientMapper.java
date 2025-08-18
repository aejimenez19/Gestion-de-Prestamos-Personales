package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.mapper;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.ClientModel;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientEntity toEntity(ClientModel clientModel);
    ClientModel toModel(ClientEntity clientEntity);
}
