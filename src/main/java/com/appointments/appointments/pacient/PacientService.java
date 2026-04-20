package com.appointments.appointments.pacient;

import com.appointments.appointments.pacient.dto.PacientRequest;
import com.appointments.appointments.pacient.dto.PacientResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacientService {
    private final PacientRepository pacientRepository;
    private final PacientMapper pacientMapper;

    public PacientService(PacientRepository pacientRepository, PacientMapper pacientMapper) {
        this.pacientRepository = pacientRepository;
        this.pacientMapper = pacientMapper;
    }

    // =========================================================================
    // METHODS FOR THE CONTROLLERS (Retrieves DTOs)
    // =========================================================================

    public PacientResponse createPacient(PacientRequest dto){
        Pacient pacient = pacientMapper.toPacient(dto);

        pacient = pacientRepository.save(pacient);

        return pacientMapper.toPacientDto(pacient);
    }

    public PacientResponse findById(Integer id){
        Pacient pacient = pacientRepository.findById(id).orElse(new Pacient());

        return pacientMapper.toPacientDto(pacient);
    }

    public List<PacientResponse> findAll(){
        return  pacientRepository.findAll()
                .stream()
                .map(pacientMapper::toPacientDto)
                .toList();
    }

    public PacientResponse updatePacient(Integer id, PacientRequest dto){
        Pacient pacient = pacientRepository.findById(id).orElse(new Pacient());
        pacient.setName(dto.name());
        pacient.setEmail(dto.email());

        pacient = pacientRepository.save(pacient);

        return pacientMapper.toPacientDto(pacient);
    }

    public void deleteById(Integer id){
        pacientRepository.deleteById(id);
    }


    // =========================================================================
    // METHODS FOR THE SERVICES (Retrieves Entities)
    // =========================================================================

    public Pacient findByIdEntity(Integer id){
        return pacientRepository.findById(id).orElse(new Pacient());
    }
}
