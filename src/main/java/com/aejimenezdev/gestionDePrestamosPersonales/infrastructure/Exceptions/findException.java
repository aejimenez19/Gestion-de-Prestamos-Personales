package com.aejimenezdev.gestionDePrestamosPersonales.infrastructure.Exceptions;

public class findException extends RuntimeException {
    public findException(String message, Throwable cause) {
        super(message, cause);
    }

    public findException(String message) {
        super(message);
    }
}
