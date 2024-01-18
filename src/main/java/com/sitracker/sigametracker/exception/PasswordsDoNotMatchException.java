package com.sitracker.sigametracker.exception;

public class PasswordsDoNotMatchException extends RuntimeException {

    public PasswordsDoNotMatchException(String message) {
        super(message);
    }
}
