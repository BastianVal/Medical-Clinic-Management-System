package com.appointments.appointments.doctor.dto;

public record DoctorRequest(
        Integer appUserId,
        String name,
        Integer specialtyId
) {
}
