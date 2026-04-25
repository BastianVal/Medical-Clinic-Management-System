package com.appointments.appointments.patient;

import com.appointments.appointments.appUser.AppUser;
import com.appointments.appointments.appUser.AppUserService;
import com.appointments.appointments.patient.dto.PatientRequest;
import com.appointments.appointments.patient.dto.PatientResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final AppUserService appUserService;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper, AppUserService appUserService) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.appUserService = appUserService;
    }

    // =========================================================================
    // METHODS FOR THE CONTROLLERS (Retrieves DTOs)
    // =========================================================================

    public PatientResponse createPacient(PatientRequest dto){
        Patient patient = patientMapper.toPacient(dto);

        patient = patientRepository.save(patient);

        return patientMapper.toPacientResponse(patient);
    }

    public PatientResponse findById(String doctorEmail, Integer id){
        AppUser appUser = appUserService.findByEmailEntity(doctorEmail);

        String role = appUser.getRole().name();

        Patient patient = new Patient();

        if(role.equals("ROLE_COORDINATOR")){
            patient = findByIdEntity(id);
        }
        else if(role.equals("ROLE_DOCTOR")){
            Integer doctorId = appUser.getDoctor().getId();
            patient = patientRepository.findByDoctorsPatient(id, doctorId)
                    .orElseThrow(() -> new EntityNotFoundException("Patient Not Found"));
        }
        return patientMapper.toPacientResponse(patient);
    }

    public List<PatientResponse> findAll(String email){
        AppUser appUser = appUserService.findByEmailEntity(email);

        String role = appUser.getRole().name();

        if(role.equals("ROLE_COORDINATOR")){
            return  findAllEntity()
                    .stream()
                    .map(patientMapper::toPacientResponse)
                    .toList();
        }
        else{
            Integer doctorId = appUser.getDoctor().getId();
            return patientRepository.findAllByDoctorsId(doctorId)
                    .stream()
                    .map(patientMapper::toPacientResponse)
                    .toList();
        }
    }

    public PatientResponse updatePacient(Integer id, PatientRequest dto){
        Patient patient = findByIdEntity(id);

        patient.setName(dto.name());
        patient.setEmail(dto.email());

        patient = patientRepository.save(patient);

        return patientMapper.toPacientResponse(patient);
    }

    public void deleteById(Integer id){
        if(!patientRepository.existsById(id)) throw new EntityNotFoundException("Patient Not Found");

        patientRepository.deleteById(id);
    }

    public List<PatientResponse> searchPatientName(String userEmail, String name){
        AppUser appUser = appUserService.findByEmailEntity(userEmail);

        String role = appUser.getRole().name();

        if(role.equals("ROLE_COORDINATOR")){
            return patientRepository.findByNameContainingIgnoreCase(name)
                    .stream()
                    .map(patientMapper::toPacientResponse)
                    .toList();
        }
        else{
            Integer doctorId = appUser.getDoctor().getId();

            return patientRepository.searchDoctorsPatientsByName(name, doctorId)
                    .stream()
                    .map(patientMapper::toPacientResponse)
                    .toList();
        }
    }

    // =========================================================================
    // METHODS FOR THE SERVICES (Retrieves Entities)
    // =========================================================================

    public Patient findByIdEntity(Integer id){
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient Not Found"));
    }

    public List<Patient> findAllEntity(){
        return patientRepository.findAll();
    }

    public Boolean existById(Integer id){
        return patientRepository.existsById(id);
    }

}
