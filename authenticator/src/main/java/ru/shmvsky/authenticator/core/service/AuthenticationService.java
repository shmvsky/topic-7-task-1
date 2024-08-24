package ru.shmvsky.authenticator.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.shmvsky.authenticator.core.entity.Account;
import ru.shmvsky.authenticator.dto.input.ConfirmationRequest;
import ru.shmvsky.authenticator.dto.input.RegistrationRequest;
import ru.shmvsky.authenticator.dto.output.AccountResponse;
import ru.shmvsky.authenticator.dto.output.CheckEmailResponse;
import ru.shmvsky.authenticator.dto.output.TokenResponse;
import ru.shmvsky.authenticator.exception.InvalidVerificationCode;
import ru.shmvsky.authenticator.exception.VerificationCodeExpiredException;
import ru.shmvsky.authenticator.port.input.ConfirmAccountUseCase;
import ru.shmvsky.authenticator.port.input.GetAccountResponseUseCase;
import ru.shmvsky.authenticator.port.input.RegisterAccountUseCase;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements RegisterAccountUseCase, ConfirmAccountUseCase, GetAccountResponseUseCase {

    private final AccountService accountService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final VerificationCodeService verificationCodeService;

    @Override
    public CheckEmailResponse register(RegistrationRequest request) {
        var account = Account.builder()
                .fullname(request.fullname())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .verified(false)
                .build();

        accountService.save(account);
        verificationCodeService.generateAndSaveVerificationCodeFor(request.email());

        return new CheckEmailResponse(request.email());
    }

    @Override
    public TokenResponse confirm(ConfirmationRequest request) {
        if (verificationCodeService.verificationCodeExists(request.email())) {
            if (verificationCodeService.verificationCodeIsValid(request.email(), request.code())) {
                accountService.verify(request.email());

                var jwt = jwtService.generateToken(accountService.loadUserByUsername(request.email()));

                return new TokenResponse(jwt);
            } else {
                throw new InvalidVerificationCode(request.email());
            }
        } else {
            verificationCodeService.generateAndSaveVerificationCodeFor(request.email());
            throw new VerificationCodeExpiredException(request.email());
        }
    }

    @Override
    public AccountResponse getByEmail(String email) {
        var account = accountService.getByEmail(email);
        return new AccountResponse(account.getFullname(), account.getEmail());
    }
}
