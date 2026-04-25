package com.appointments.appointments.appoinment.dto;

import com.appointments.appointments.appointmentStatus.AppointmentStatusEnum;

import java.time.LocalDateTime;

public record AppointmentResponse(
        Integer id,
        LocalDateTime dateTime,
        Integer patientId,
        String patientName,
        Integer doctorId,
        String doctorName,
        Integer roomId,
        Integer roomNumber,
        AppointmentStatusEnum status
) {
}
