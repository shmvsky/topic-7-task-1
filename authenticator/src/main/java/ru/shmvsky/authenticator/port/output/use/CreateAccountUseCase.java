package ru.shmvsky.authenticator.port.output.use;

import ru.shmvsky.authenticator.core.entity.Account;

public interface CreateAccountUseCase {

    Account save(Account account);

}
