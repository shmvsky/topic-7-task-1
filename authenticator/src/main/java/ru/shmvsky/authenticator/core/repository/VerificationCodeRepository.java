package ru.shmvsky.authenticator.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shmvsky.authenticator.core.entity.VerificationCode;

import java.time.LocalDateTime;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    boolean existsByEmailAndExpAfter(String email, LocalDateTime now);
    boolean existsByEmailAndCodeAndExpAfter(String email, String code, LocalDateTime now);
}