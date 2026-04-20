package com.appointments.appointments.appoinment.dto;

import com.appointments.appointments.appointmentStatus.AppointmentStatusEnum;

import java.time.LocalDateTime;

public record AppointmentResponse(
        Integer id,
        LocalDateTime dateTime,
        String pacientName,
        String doctorName,
        Integer roomNumber,
        AppointmentStatusEnum status
) {
}
