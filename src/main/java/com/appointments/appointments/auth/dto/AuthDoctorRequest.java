package com.appointments.appointments.auth.dto;

import com.appointments.appointments.auth.role.Role;

public record AuthDoctorRequest(
        String email,
        String password,
        Role role,
        String name,
        Integer specialtyId
) {
}
