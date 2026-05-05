package com.appointments.appointments.auth.dto;

public record LoginResponse(
        String token,
        Integer accountId,
        String email,
        String role,
        Integer profileId,
        String name
) {
}
