package com.appointments.appointments.patient.dto;

public record PatientResponse(
        Integer id,
        String name,
        String email
) {
}
