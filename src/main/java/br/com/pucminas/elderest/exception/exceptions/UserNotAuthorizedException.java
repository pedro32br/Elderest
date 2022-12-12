package br.com.pucminas.elderest.exception.exceptions;

public class UserNotAuthorizedException extends RuntimeException {
    public UserNotAuthorizedException(final String message) {
        super(message);
    }
}
