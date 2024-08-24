package ru.shmvsky.authenticator.core.controller.input;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.shmvsky.authenticator.core.service.AuthenticationService;
import ru.shmvsky.authenticator.dto.input.ConfirmationRequest;
import ru.shmvsky.authenticator.dto.input.RegistrationRequest;
import ru.shmvsky.authenticator.dto.output.CheckEmailResponse;
import ru.shmvsky.authenticator.dto.output.TokenResponse;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public CheckEmailResponse register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return authenticationService.register(registrationRequest);
    }

    @PostMapping("/confirm")
    public TokenResponse confirm(@Valid @RequestBody ConfirmationRequest confirmationRequest) {
        return authenticationService.confirm(confirmationRequest);
    }

}
