package com.appointments.appointments.appointmentStatus;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppointmentStatusService {

    private final AppointmentStatusRepository appointmentStatusRepository;

    public AppointmentStatusService(AppointmentStatusRepository appointmentStatusRepository) {
        this.appointmentStatusRepository = appointmentStatusRepository;
    }

    // =========================================================================
    // METHODS FOR THE CONTROLLERS (Retrieves DTOs)
    // =========================================================================

    public AppointmentStatus findById(Integer id){
        return appointmentStatusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AppointmentStatus Not Found"));
    }

    // =========================================================================
    // METHODS FOR THE SERVICES (Retrieves Entities)
    // =========================================================================

    public AppointmentStatus findByIdEntity(Integer id){
        return appointmentStatusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AppointmentStatus Not Found"));
    }
}
