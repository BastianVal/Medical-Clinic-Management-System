package com.appointments.appointments.patient;

import com.appointments.appointments.patient.dto.PatientRequest;
import com.appointments.appointments.patient.dto.PatientResponse;
import org.springframework.stereotype.Service;

@Service
public class PatientMapper {
    public Patient toPacient(PatientRequest dto){
        Patient patient = new Patient();

        patient.setName(dto.name());
        patient.setEmail(dto.email());

        return patient;
    }

    public PatientResponse toPacientResponse(Patient patient){
        return new PatientResponse(
                patient.getId(),
                patient.getName(),
                patient.getEmail()
        );
    }
}
