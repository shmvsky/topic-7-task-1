package ru.shmvsky.authenticator.dto.output;

import lombok.Value;

@Value
public class CheckEmailResponse {

    String msg;

    public CheckEmailResponse(String email) {
        this.msg = "Код подтверждения отправлен на почту: " + email;
    }

}
