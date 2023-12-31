package org.agenda.repo;

public class CreationException extends RuntimeException {
    public CreationException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public CreationException(String errorMessage) {
        super(errorMessage);
    }

    public CreationException(Throwable cause) { super(cause); }
}
