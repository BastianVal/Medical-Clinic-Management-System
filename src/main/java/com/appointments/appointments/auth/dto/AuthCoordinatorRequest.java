package com.appointments.appointments.auth.dto;

import com.appointments.appointments.auth.role.Role;

public record AuthCoordinatorRequest(
        String email,
        String password,
        Role role,
        String name
) {
}
