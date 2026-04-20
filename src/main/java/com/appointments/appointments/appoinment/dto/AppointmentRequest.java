package com.appointments.appointments.appoinment.dto;

import java.time.LocalDateTime;

public record AppointmentRequest(
        LocalDateTime dateTime,
        Integer pacientId,
        Integer doctorId,
        Integer roomId
        //Integer statusId
) {
}
