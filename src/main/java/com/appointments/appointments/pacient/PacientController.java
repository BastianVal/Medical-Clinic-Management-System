package com.appointments.appointments.pacient;

import com.appointments.appointments.pacient.pacientDtos.PacientDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pacient")
public class PacientController {
    private final PacientService pacientService;

    public PacientController(PacientService pacientService) {
        this.pacientService = pacientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacientDto createPacient(@RequestBody PacientDto pacientDto){
        return pacientService.createPacient(pacientDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PacientDto findById(@PathVariable Integer id){
        return pacientService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PacientDto> findAll(){
        return pacientService.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id){
        pacientService.deleteById(id);
    }
}
