package com.appointments.appointments.doctor;

import com.appointments.appointments.doctor.doctorDtos.DoctorDto;
import com.appointments.appointments.doctor.doctorDtos.DoctorDtoResponse;
import com.appointments.appointments.doctorSpecialty.DoctorSpecialty;
import com.appointments.appointments.doctorSpecialty.DoctorSpecialtyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;


@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorSpecialtyRepository doctorSpecialtyRepository;
    private final DoctorMapper doctorMapper;


    public DoctorService(DoctorRepository doctorRepository, DoctorSpecialtyRepository doctorSpecialtyRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorSpecialtyRepository = doctorSpecialtyRepository;
        this.doctorMapper = doctorMapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDtoResponse createDoctor(DoctorDto dto){
        DoctorSpecialty doctorSpecialty = doctorSpecialtyRepository.findById(dto.specialtyId()).orElse(new DoctorSpecialty());

        Doctor doctor = doctorMapper.toDoctor(dto, doctorSpecialty);

        doctorRepository.save(doctor);

        return doctorMapper.toDoctorDtoResponse(doctor);
    }

    @ResponseStatus(HttpStatus.OK)
    public DoctorDtoResponse findById(Integer id){
        Doctor doctor = doctorRepository.findById(id).orElse(new Doctor());

        return doctorMapper.toDoctorDtoResponse(doctor);
    }

    @ResponseStatus(HttpStatus.OK)
    public List<DoctorDtoResponse> findAll(){
        return  doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDoctorDtoResponse)
                .toList();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Integer id){
        doctorRepository.deleteById(id);
    }
}
