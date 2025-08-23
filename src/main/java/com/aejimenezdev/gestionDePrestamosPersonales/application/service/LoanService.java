package com.aejimenezdev.gestionDePrestamosPersonales.application.service;

import com.aejimenezdev.gestionDePrestamosPersonales.application.usercase.LoanUserCase;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.model.LoanModel;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.repository.ClientRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.domain.repository.LoanRepository;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions.ClientExitException;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.entity.LoanEntity;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.request.LoanDtoRequest;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.LoanDtoResponse;
import com.aejimenezdev.gestionDePrestamosPersonales.web.mapper.LoanWebMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanService implements LoanUserCase {

    private final ClientRepository clientRepository;
    private final LoanRepository loanRepository;
    private final LoanWebMapper loanWebMapper;

    @Override
    public LoanDtoResponse saveLoan(LoanDtoRequest loanDtoRequest) {
        log.info("Starting loan save: {}", loanDtoRequest);
        if (!clientRepository.existsById(loanDtoRequest.getClientId())) {
            log.warn("The client with the identification number {} not exists", loanDtoRequest.getClientId());
            throw new ClientExitException("The client not exists with the provided ID number");
        }

        if (loanDtoRequest.getStartDate() == null || loanDtoRequest.getStartDate().toString().isBlank()) {
            LocalDate localDate = LocalDate.now();
            loanDtoRequest.setStartDate(localDate);
        }

        LoanModel loanModel = loanRepository.saveLoan(loanWebMapper.toModel(loanDtoRequest));
        return loanWebMapper.toDtoResponse(loanModel);
    }
}
