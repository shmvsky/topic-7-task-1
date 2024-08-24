package ru.shmvsky.authenticator.exception;

import lombok.Getter;

@Getter
public class InvalidVerificationCode extends RuntimeException {

    private final String email;

    public InvalidVerificationCode(String email) {
        super("Код подтверждения введен некорректно");
        this.email = email;
    }

}
