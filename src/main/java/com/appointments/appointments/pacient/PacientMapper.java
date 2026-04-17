package com.appointments.appointments.pacient;

import com.appointments.appointments.doctor.Doctor;
import com.appointments.appointments.doctor.doctorDtos.DoctorDto;
import com.appointments.appointments.doctor.doctorDtos.DoctorDtoResponse;
import com.appointments.appointments.doctorSpecialty.DoctorSpecialty;
import com.appointments.appointments.pacient.pacientDtos.PacientDto;
import org.springframework.stereotype.Service;

@Service
public class PacientMapper {
    public Pacient toPacient(PacientDto dto){
        Pacient pacient = new Pacient();

        pacient.setName(dto.name());
        pacient.setEmail(dto.email());

        return pacient;
    }

    public PacientDto toPacientDto(Pacient pacient){
        return new PacientDto(
                pacient.getName(),
                pacient.getEmail()
        );
    }
}
