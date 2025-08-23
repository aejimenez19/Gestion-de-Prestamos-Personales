package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions;

public class SaveException extends RuntimeException{
    public SaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveException(String message) {
        super(message);
    }
}
