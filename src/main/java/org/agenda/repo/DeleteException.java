package org.agenda.repo;

public class DeleteException extends RuntimeException{
    public DeleteException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public DeleteException(String errorMessage) {
        super(errorMessage);
    }

    public DeleteException(Throwable cause) { super(cause); }
}
