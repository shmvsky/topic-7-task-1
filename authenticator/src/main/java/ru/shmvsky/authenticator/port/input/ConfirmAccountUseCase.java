package ru.shmvsky.authenticator.port.input;

import ru.shmvsky.authenticator.dto.input.ConfirmationRequest;
import ru.shmvsky.authenticator.dto.output.TokenResponse;

public interface ConfirmAccountUseCase {

    TokenResponse confirm(ConfirmationRequest request);

}
