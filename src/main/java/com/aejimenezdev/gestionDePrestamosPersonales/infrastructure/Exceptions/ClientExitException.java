package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions;

public class ClientExitException extends RuntimeException{

    public ClientExitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientExitException(String message) {
        super(message);
    }
}
