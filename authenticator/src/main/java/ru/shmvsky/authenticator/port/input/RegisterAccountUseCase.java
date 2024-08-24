package ru.shmvsky.authenticator.port.input;

import ru.shmvsky.authenticator.dto.input.RegistrationRequest;
import ru.shmvsky.authenticator.dto.output.CheckEmailResponse;

public interface RegisterAccountUseCase {

    CheckEmailResponse register(RegistrationRequest request);

}
