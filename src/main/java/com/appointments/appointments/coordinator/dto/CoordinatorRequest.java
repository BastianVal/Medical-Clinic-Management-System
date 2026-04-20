package com.appointments.appointments.coordinator.dto;

public record CoordinatorRequest(
        Integer appUserId,
        String name
) {
}
