package com.appointments.appointments.doctor.dto;

import com.appointments.appointments.doctorSpecialty.DoctorSpecialtyEnum;

public record DoctorResponse(
        Integer id,
        String name,
        String email,
        Integer specialtyId,
//        DoctorSpecialtyEnum specialty,
        String specialty,
        Integer appUserId,
        Boolean isActive
) {
}
