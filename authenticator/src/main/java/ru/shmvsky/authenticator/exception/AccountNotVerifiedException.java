package ru.shmvsky.authenticator.exception;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AccountNotVerifiedException extends RuntimeException {

    private final String email;

    public AccountNotVerifiedException(@NotNull @Email String email) {
        super("Необходимо подтвердить почту");
        this.email = email;
    }
}
