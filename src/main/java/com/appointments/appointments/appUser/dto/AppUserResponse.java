package com.appointments.appointments.appUser.dto;

public record AppUserResponse(
        Integer id,
        String email,
        String role,
        Boolean isActive,
        Integer profileId
) {
}
