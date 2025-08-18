package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions;

public class ClientSaveException extends RuntimeException{
    public ClientSaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientSaveException(String message) {
        super(message);
    }
}
