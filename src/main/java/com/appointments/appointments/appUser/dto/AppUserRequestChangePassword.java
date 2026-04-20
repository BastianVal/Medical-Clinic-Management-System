package com.appointments.appointments.appUser.dto;

public record AppUserRequestChangePassword(
        String oldPassword,
        String newPassword
) {
}
