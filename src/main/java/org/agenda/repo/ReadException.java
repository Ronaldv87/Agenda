package org.agenda.repo;

public class ReadException extends RuntimeException{
    public ReadException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public ReadException(String errorMessage) {
        super(errorMessage);
    }

    public ReadException(Throwable cause) { super(cause); }
}
