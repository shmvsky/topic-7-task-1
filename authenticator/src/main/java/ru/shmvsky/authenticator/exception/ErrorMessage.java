package ru.shmvsky.authenticator.exception;

import java.time.Instant;

public record ErrorMessage(
        String status,
        Instant timestamp,
        String message,
        String description
) {
}
