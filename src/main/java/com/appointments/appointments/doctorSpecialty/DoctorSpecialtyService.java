package com.appointments.appointments.doctorSpecialty;

import com.appointments.appointments.doctorSpecialty.dto.DoctorSpecialtyRequest;
import com.appointments.appointments.doctorSpecialty.dto.DoctorSpecialtyResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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

    @CacheEvict(value = "specialtiesCache", allEntries = true)
    public DoctorSpecialtyResponse createSpecialty(DoctorSpecialtyRequest doctorSpecialtyRequest){
        DoctorSpecialty doctorSpecialty = new DoctorSpecialty();
        doctorSpecialty.setSpecialty(doctorSpecialtyRequest.name());
        doctorSpecialty.setDoctors(new ArrayList<>());

        doctorSpecialty = doctorSpecialtyRepository.save(doctorSpecialty);

        return new DoctorSpecialtyResponse(doctorSpecialty.getId() ,doctorSpecialty.getSpecialty());
    }

    @Cacheable("specialtiesCache")
    public List<DoctorSpecialtyResponse> findAll(){
        return doctorSpecialtyRepository.findAll()
                .stream()
                .map(specialty ->
                        new DoctorSpecialtyResponse(
                        specialty.getId(),
//                        specialty.getSpecialty().name()
                        specialty.getSpecialty()
                        )
                )
                .toList();
    }

    @CacheEvict(value = "specialtiesCache", allEntries = true)
    public DoctorSpecialtyResponse updateSpecialty(DoctorSpecialtyRequest doctorSpecialtyRequest, Integer id){
        DoctorSpecialty doctorSpecialty = findByIdEntity(id);
        doctorSpecialty.setSpecialty(doctorSpecialtyRequest.name());

        doctorSpecialty = doctorSpecialtyRepository.save(doctorSpecialty);

        return new DoctorSpecialtyResponse(doctorSpecialty.getId() ,doctorSpecialty.getSpecialty());
    }

    @CacheEvict(value = "specialtiesCache", allEntries = true)
    public void deleteSpecialty(Integer id){
        doctorSpecialtyRepository.deleteById(id);
    }

    // =========================================================================
    // METHODS FOR THE SERVICES (Retrieves Entities)
    // =========================================================================

    public DoctorSpecialty findByIdEntity(Integer id){
        return doctorSpecialtyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DoctorSpecialty Not Found"));
    }


}
