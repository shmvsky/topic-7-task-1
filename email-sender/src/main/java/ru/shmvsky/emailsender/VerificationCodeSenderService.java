package ru.shmvsky.emailsender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VerificationCodeSenderService {

    @KafkaListener(topics = "verification_codes", groupId = "verification-1")
    public void sendVerificationCode(String code) {
        log.info("Verification code '" + code + "' has been sent successfully!");
    }


}
