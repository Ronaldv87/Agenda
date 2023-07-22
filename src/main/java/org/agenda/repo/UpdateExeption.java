package org.agenda.repo;

public class UpdateExeption extends RuntimeException{
    public UpdateExeption(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public UpdateExeption(String errorMessage) {
        super(errorMessage);
    }

    public UpdateExeption(Throwable cause) { super(cause); }
}
