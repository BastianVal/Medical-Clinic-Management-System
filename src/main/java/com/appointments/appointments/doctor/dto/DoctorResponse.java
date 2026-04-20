package com.appointments.appointments.doctor.dto;

import com.appointments.appointments.doctorSpecialty.DoctorSpecialtyEnum;

public record DoctorResponse(
        Integer id,
        String name,
        String email,
        DoctorSpecialtyEnum specialty,
        Integer appUserId
) {
}
