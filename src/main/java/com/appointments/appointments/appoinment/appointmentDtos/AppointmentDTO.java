package com.appointments.appointments.appoinment.appointmentDtos;

import java.time.LocalDateTime;

public record AppointmentDTO(
        LocalDateTime dateTime,
        Integer pacientId,
        Integer doctorId,
        Integer roomId,
        Integer statusId
) {
}
