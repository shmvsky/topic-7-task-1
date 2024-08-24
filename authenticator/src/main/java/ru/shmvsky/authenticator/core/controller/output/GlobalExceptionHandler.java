package ru.shmvsky.authenticator.core.controller.output;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.shmvsky.authenticator.exception.AccountNotVerifiedException;
import ru.shmvsky.authenticator.exception.ErrorMessage;
import ru.shmvsky.authenticator.exception.InvalidVerificationCode;
import ru.shmvsky.authenticator.exception.VerificationCodeExpiredException;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = VerificationCodeExpiredException.class)
    @ResponseStatus(value = HttpStatus.GONE)
    public ErrorMessage handleVerificationCodeExpiredException(VerificationCodeExpiredException ex) {
        return new ErrorMessage(
                HttpStatus.GONE.name(),
                Instant.now(),
                ex.getMessage(),
                "Срок действия кода истек. На вашу почту был отправлен новый код подтверждения"
        );
    }

    @ExceptionHandler(value = InvalidVerificationCode.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleInvalidVerificationCode(InvalidVerificationCode ex) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.name(),
                Instant.now(),
                ex.getMessage(),
                "Неправильный код подтверждения. Повторите попытку снова"
        );
    }

    @ExceptionHandler(value = AccountNotVerifiedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleAccountNotVerifiedException(AccountNotVerifiedException ex) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.name(),
                Instant.now(),
                ex.getMessage(),
                "Чтобы выйти в аккаунт необходимо подтвердить почту"
        );
    }



}
