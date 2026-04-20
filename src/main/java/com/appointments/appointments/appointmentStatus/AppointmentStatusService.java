package com.appointments.appointments.appointmentStatus;

import org.springframework.stereotype.Service;

@Service
public class AppointmentStatusService {

    private final AppointmentStatusRepository appointmentStatusRepository;

    public AppointmentStatusService(AppointmentStatusRepository appointmentStatusRepository) {
        this.appointmentStatusRepository = appointmentStatusRepository;
    }

    public AppointmentStatus findById(Integer id){
        return appointmentStatusRepository.findById(id).orElse(new AppointmentStatus());
    }
}
