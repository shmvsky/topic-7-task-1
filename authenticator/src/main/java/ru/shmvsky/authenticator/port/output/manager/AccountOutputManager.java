package ru.shmvsky.authenticator.port.output.manager;

import ru.shmvsky.authenticator.core.entity.Account;

public interface AccountOutputManager {
    Account save(Account account);

    Account getByEmail(String email);
    void verify(String email);
}
