package ru.shmvsky.authenticator.core.controller.input;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shmvsky.authenticator.core.service.AuthenticationService;
import ru.shmvsky.authenticator.dto.output.AccountResponse;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AuthenticationService authenticationService;

    @GetMapping("/account")
    public AccountResponse accountInfo(Principal principal) {
        return authenticationService.getByEmail(principal.getName());
    }

}
