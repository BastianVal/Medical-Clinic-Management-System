package com.appointments.appointments.pacient;

import com.appointments.appointments.pacient.dto.PacientRequest;
import com.appointments.appointments.pacient.dto.PacientResponse;
import org.springframework.stereotype.Service;

@Service
public class PacientMapper {
    public Pacient toPacient(PacientRequest dto){
        Pacient pacient = new Pacient();

        pacient.setName(dto.name());
        pacient.setEmail(dto.email());

        return pacient;
    }

    public PacientResponse toPacientDto(Pacient pacient){
        return new PacientResponse(
                pacient.getId(),
                pacient.getName(),
                pacient.getEmail()
        );
    }
}
