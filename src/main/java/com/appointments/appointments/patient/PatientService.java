package com.appointments.appointments.patient;

import com.appointments.appointments.patient.dto.PatientRequest;
import com.appointments.appointments.patient.dto.PatientResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    // =========================================================================
    // METHODS FOR THE CONTROLLERS (Retrieves DTOs)
    // =========================================================================

    public PatientResponse createPacient(PatientRequest dto){
        Patient patient = patientMapper.toPacient(dto);

        patient = patientRepository.save(patient);

        return patientMapper.toPacientResponse(patient);
    }

    public PatientResponse findById(Integer id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient Not Found"));

        return patientMapper.toPacientResponse(patient);
    }

    public List<PatientResponse> findAll(){
        return  patientRepository.findAll()
                .stream()
                .map(patientMapper::toPacientResponse)
                .toList();
    }

    public PatientResponse updatePacient(Integer id, PatientRequest dto){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient Not Found"));

        patient.setName(dto.name());
        patient.setEmail(dto.email());

        patient = patientRepository.save(patient);

        return patientMapper.toPacientResponse(patient);
    }

    public void deleteById(Integer id){
        if(!patientRepository.existsById(id)) throw new EntityNotFoundException("Patient Not Found");

        patientRepository.deleteById(id);
    }

    public List<PatientResponse> findByDoctorsPatient(String name, Integer doctorId){

        return patientRepository.findByDoctorsPatient(name, doctorId)
                .stream()
                .map(patientMapper::toPacientResponse)
                .toList();
    }

    // =========================================================================
    // METHODS FOR THE SERVICES (Retrieves Entities)
    // =========================================================================

    public Patient findByIdEntity(Integer id){
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient Not Found"));
    }

    public Boolean existById(Integer id){
        return patientRepository.existsById(id);
    }

}
