package com.appointments.appointments.doctorSpecialty;

import org.springframework.stereotype.Service;

@Service
public class DoctorSpecialtyService {

    private final DoctorSpecialtyRepository doctorSpecialtyRepository;

    public DoctorSpecialtyService(DoctorSpecialtyRepository doctorSpecialtyRepository) {
        this.doctorSpecialtyRepository = doctorSpecialtyRepository;
    }

    // =========================================================================
    // METHODS FOR THE CONTROLLERS (Retrieves DTOs)
    // =========================================================================


    // =========================================================================
    // METHODS FOR THE SERVICES (Retrieves Entities)
    // =========================================================================

    public DoctorSpecialty findByIdEntity(Integer id){
        return doctorSpecialtyRepository.findById(id).orElse(new DoctorSpecialty());
    }

    public DoctorSpecialty findDoctorSpecialtyByIdEntity(Integer id){
        return doctorSpecialtyRepository.findById(id).orElse(new DoctorSpecialty());
    }
}
