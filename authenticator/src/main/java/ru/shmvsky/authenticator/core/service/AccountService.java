package ru.shmvsky.authenticator.core.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.shmvsky.authenticator.core.entity.Account;
import ru.shmvsky.authenticator.port.output.manager.AccountOutputManager;
import ru.shmvsky.authenticator.port.output.use.CreateAccountUseCase;
import ru.shmvsky.authenticator.port.output.use.GetAccountUseCase;
import ru.shmvsky.authenticator.port.output.use.VerifyAccountUseCase;

import java.util.List;

@Service
public class AccountService implements UserDetailsService, CreateAccountUseCase, GetAccountUseCase, VerifyAccountUseCase {

    private final AccountOutputManager accountOutputManager;

    public AccountService(AccountOutputManager accountOutputManager) {
        this.accountOutputManager = accountOutputManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = getByEmail(username);
        return new User(account.getEmail(), "", List.of(new SimpleGrantedAuthority("USER")));
    }

    @Override
    public Account getByEmail(String email) {
        return accountOutputManager.getByEmail(email);
    }

    @Override
    public Account save(Account account) {
        return accountOutputManager.save(account);
    }

    @Override
    public void verify(String email) {
        accountOutputManager.verify(email);
    }
}
