package com.appointments.appointments.doctorSpecialty;

import com.appointments.appointments.doctorSpecialty.dto.DoctorSpecialtyResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorSpecialtyService {

    private final DoctorSpecialtyRepository doctorSpecialtyRepository;

    public DoctorSpecialtyService(DoctorSpecialtyRepository doctorSpecialtyRepository) {
        this.doctorSpecialtyRepository = doctorSpecialtyRepository;
    }

    // =========================================================================
    // METHODS FOR THE CONTROLLERS (Retrieves DTOs)
    // =========================================================================

    public List<DoctorSpecialtyResponse> findAll(){
        return doctorSpecialtyRepository.findAll()
                .stream()
                .map(specialty ->
                        new DoctorSpecialtyResponse(
                        specialty.getId(),
                        specialty.getSpecialty().name()
                        )
                )
                .toList();
    }

    // =========================================================================
    // METHODS FOR THE SERVICES (Retrieves Entities)
    // =========================================================================

    public DoctorSpecialty findByIdEntity(Integer id){
        return doctorSpecialtyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DoctorSpecialty Not Found"));
    }
}
