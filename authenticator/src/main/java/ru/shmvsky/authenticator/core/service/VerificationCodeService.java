package ru.shmvsky.authenticator.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.shmvsky.authenticator.core.entity.VerificationCode;
import ru.shmvsky.authenticator.core.repository.VerificationCodeRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VerificationCodeService {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6;
    private final SecureRandom random = new SecureRandom();

    public static final String TOPIC = "verification_codes";

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final VerificationCodeRepository verificationCodeRepository;

    public void generateAndSaveVerificationCodeFor(String email) {
        String code = generateVerificationCode();
        LocalDateTime exp = LocalDateTime.now().plusMinutes(3);

        VerificationCode verificationCode = VerificationCode.builder()
                        .email(email)
                        .code(code)
                        .exp(exp)
                        .build();

        verificationCodeRepository.save(verificationCode);

        kafkaTemplate.send(TOPIC, verificationCode.getEmail(), verificationCode.getCode());
    }

    private String generateVerificationCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }

    public boolean verificationCodeIsValid(String email, String code) {
        return verificationCodeRepository.existsByEmailAndCodeAndExpAfter(email, code, LocalDateTime.now());
    }

    public boolean verificationCodeExists(String email) {
        return verificationCodeRepository.existsByEmailAndExpAfter(email, LocalDateTime.now());
    }

}
