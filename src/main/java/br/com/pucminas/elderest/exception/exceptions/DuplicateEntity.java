package br.com.pucminas.elderest.exception.exceptions;

public class DuplicateEntity extends RuntimeException {
    public DuplicateEntity(final String message) {
        super(message);
    }
}
