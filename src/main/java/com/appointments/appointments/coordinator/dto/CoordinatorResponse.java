package com.appointments.appointments.coordinator.dto;

public record CoordinatorResponse(
        Integer id,
        String name,
        String email,
        Integer appUserId
) {
}
