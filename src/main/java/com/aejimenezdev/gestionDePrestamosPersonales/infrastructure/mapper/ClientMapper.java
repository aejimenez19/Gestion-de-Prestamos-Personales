package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.mapper;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.ClientModel;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(target = "loans", ignore = true)
    ClientEntity toEntity(ClientModel clientModel);

    @Mapping(target = "loans", ignore = true)
    ClientModel toModel(ClientEntity clientEntity);
}
