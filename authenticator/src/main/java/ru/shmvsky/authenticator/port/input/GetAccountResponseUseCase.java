package ru.shmvsky.authenticator.port.input;

import ru.shmvsky.authenticator.dto.output.AccountResponse;

public interface GetAccountResponseUseCase {

    AccountResponse getByEmail(String email);

}
