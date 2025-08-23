package com.aejimenezdev.gestionDePrestamosPersonales.web.exception;

import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions.ClientExitException;
import com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions.SaveException;
import com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SaveException.class)
    public ResponseEntity<ErrorResponse> SaveException(SaveException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationException(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Validation error: " + ex.getBindingResult().getFieldError().getDefaultMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ClientExitException.class)
    public ResponseEntity<ErrorResponse> clientExitException(ClientExitException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.CONFLICT.value()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> RunTimeException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
