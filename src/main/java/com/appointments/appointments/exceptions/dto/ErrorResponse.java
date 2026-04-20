package com.appointments.appointments.exceptions.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,         // Ej. 400, 404, 500
        String error,       // Ej. "Not Found", "Bad Request"
        String message,     // Ej. "El doctor no existe"
        String path        // Ej. "/api/doctors/15"
) {
}
