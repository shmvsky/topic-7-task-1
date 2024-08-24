package ru.shmvsky.authenticator.dto.output;

public record AccountResponse (
        String fullname,
        String email
) {
}
