package ru.shmvsky.authenticator.core.adapter;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.shmvsky.authenticator.core.entity.Account;
import ru.shmvsky.authenticator.core.repository.AccountRepository;
import ru.shmvsky.authenticator.port.output.manager.AccountOutputManager;

@Component
public class AccountAdapter implements AccountOutputManager {

    private final AccountRepository accountRepository;

    public AccountAdapter(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public void verify(String email) {
        var account = getByEmail(email);
        account.setVerified(true);
        save(account);
    }
}
