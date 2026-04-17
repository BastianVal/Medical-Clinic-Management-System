package com.appointments.appointments.doctor.doctorDtos;

import com.appointments.appointments.doctorSpecialty.DoctorSpecialtyEnum;

public record DoctorDtoResponse(
        String name,
        DoctorSpecialtyEnum specialty
) {
}
