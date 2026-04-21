package com.appointments.appointments.doctor;

import com.appointments.appointments.doctor.dto.DoctorRequest;
import com.appointments.appointments.doctor.dto.DoctorResponse;
import com.appointments.appointments.doctorSpecialty.DoctorSpecialtyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final DoctorSpecialtyService doctorSpecialtyService;


    public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper, DoctorSpecialtyService doctorSpecialtyService) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
        this.doctorSpecialtyService = doctorSpecialtyService;
    }

    // =========================================================================
    // METHODS FOR THE CONTROLLERS (Retrieves DTOs)
    // =========================================================================

    public DoctorResponse findById(Integer id){
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor Not Found"));

        return doctorMapper.toDoctorDtoResponse(doctor);
    }

    public List<DoctorResponse> findAll(){
        return  doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDoctorDtoResponse)
                .toList();
    }

    public DoctorResponse updateDoctor(Integer id, DoctorRequest dto){
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor Not Found"));

        doctor.setName(dto.name());

        doctor.setDoctorSpecialty(
                doctorSpecialtyService.findByIdEntity(dto.specialtyId())
        );

        doctor = doctorRepository.save(doctor);

        return doctorMapper.toDoctorDtoResponse(doctor);
    }

    public void deleteById(Integer id){
        if(!doctorRepository.existsById(id)) throw new EntityNotFoundException("Doctor Not Found");

        doctorRepository.deleteById(id);
    }

    // =========================================================================
    // METHODS FOR THE SERVICES (Retrieves Entities)
    // =========================================================================


    public Doctor createDoctorEntity(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public Doctor findByIdEntity(Integer id){
        return doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor Not Found"));
    }

    public Boolean existById(Integer id){
        return doctorRepository.existsById(id);
    }
}
