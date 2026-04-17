package com.appointments.appointments.pacient;

import com.appointments.appointments.pacient.pacientDtos.PacientDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

@Service
public class PacientService {
    private final PacientRepository pacientRepository;
    private final PacientMapper pacientMapper;

    public PacientService(PacientRepository pacientRepository, PacientMapper pacientMapper) {
        this.pacientRepository = pacientRepository;
        this.pacientMapper = pacientMapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    public PacientDto createPacient(PacientDto dto){
        Pacient pacient = pacientMapper.toPacient(dto);

        pacientRepository.save(pacient);

        return dto;
    }

    @ResponseStatus(HttpStatus.OK)
    public PacientDto findById(Integer id){
        Pacient pacient = pacientRepository.findById(id).orElse(new Pacient());

        return pacientMapper.toPacientDto(pacient);
    }

    @ResponseStatus(HttpStatus.OK)
    public List<PacientDto> findAll(){
        return  pacientRepository.findAll()
                .stream()
                .map(pacientMapper::toPacientDto)
                .toList();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Integer id){
        pacientRepository.deleteById(id);
    }
}
