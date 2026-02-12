package com.aejimenezdev.gestionDePrestamosPersonales.application.service;

import com.aejimenezdev.gestionDePrestamosPersonales.application.usercase.ClientUserCase;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.ClientModel;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.LoanModel;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.repository.ClientRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.repository.LoanRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions.ClientExitException;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions.SaveException;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.ClientDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.ClientDtoResponse;
import com.aejimenezdev.gestionDePrestamosPersonales.web.mapper.ClientWebMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService implements ClientUserCase {
    private final LoanRepository loanRepository;
    private final ClientWebMapper clientWebMapper;

    @Override
    public List<ClientDtoResponse> findAllProviderWithLoan(Long clientId) {
        List<LoanModel> loanModels = loanRepository.findAllLoansByClientId(clientId);
        return loanModels.stream()
                .map(LoanModel::getProviderId)
                .distinct()
                .map(clientWebMapper::toDtoResponse)
                .toList();
    }
}
