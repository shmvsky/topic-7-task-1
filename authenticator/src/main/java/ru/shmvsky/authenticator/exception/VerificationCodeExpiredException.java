package ru.shmvsky.authenticator.exception;

public class VerificationCodeExpiredException extends RuntimeException {

    private final String email;

    public VerificationCodeExpiredException(String email) {
        super("Срок действия кода истек");
        this.email = email;
    }
}
