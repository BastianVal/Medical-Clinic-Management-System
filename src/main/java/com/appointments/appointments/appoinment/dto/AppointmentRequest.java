package com.appointments.appointments.appoinment.dto;

import java.time.LocalDateTime;

public record AppointmentRequest(
        LocalDateTime dateTime,
        Integer patientId,
        Integer doctorId,
        Integer roomId
        //Integer statusId
) {
}
