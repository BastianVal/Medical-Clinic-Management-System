package com.appointments.appointments.appUser.dto;

import com.appointments.appointments.auth.role.Role;

public record AppUserRequest(
        String email,
        String password,
        Role role
) {
}
