package com.appointments.appointments.appoinment.appointmentDtos;

import com.appointments.appointments.appointmentStatus.AppointmentStatusEnum;

import java.time.LocalDateTime;

public record AppointmentDtoResponse(
        LocalDateTime dateTime,
        String pacientName,
        String doctorName,
        Integer roomNumber,
        AppointmentStatusEnum status
) {
}
