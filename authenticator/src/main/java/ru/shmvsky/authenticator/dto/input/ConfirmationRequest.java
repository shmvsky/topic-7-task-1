package ru.shmvsky.authenticator.dto.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConfirmationRequest(
        @NotNull
        @Email
        String email,
        @NotBlank
        String code) {
}
