package com.aejimenezdev.gestionDePrestamosPersonales.web.mapper;

import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.ClientModel;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.ClientDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.ClientDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientWebMapper {

    ClientModel toModel(ClientDtoRequest clientDtoRequest);

    ClientDtoResponse toDtoResponse(ClientModel clientModel);
}
