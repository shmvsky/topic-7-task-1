package ru.shmvsky.authenticator.port.output.use;

import ru.shmvsky.authenticator.core.entity.Account;

public interface GetAccountUseCase {

    Account getByEmail(String email);

}
