package com.appointments.appointments.doctor;

import com.appointments.appointments.doctor.doctorDtos.DoctorDto;
import com.appointments.appointments.doctor.doctorDtos.DoctorDtoResponse;
import com.appointments.appointments.doctorSpecialty.DoctorSpecialty;
import org.springframework.stereotype.Service;

@Service
public class DoctorMapper {
    public Doctor toDoctor(DoctorDto dto, DoctorSpecialty doctorSpecialty){
        Doctor doctor =  new Doctor();
        doctor.setName(dto.name());
        doctor.setDoctorSpecialty(doctorSpecialty);

        return doctor;
    }

    public DoctorDtoResponse toDoctorDtoResponse(Doctor doctor){
        return new DoctorDtoResponse(
                doctor.getName(),
                doctor.getDoctorSpecialty().getSpecialty()
        );
    }
}
