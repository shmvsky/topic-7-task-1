package ru.shmvsky.authenticator.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shmvsky.authenticator.core.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);

}
