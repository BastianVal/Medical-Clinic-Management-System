package com.appointments.appointments.doctor;

import com.appointments.appointments.doctor.doctorDtos.DoctorDto;
import com.appointments.appointments.doctor.doctorDtos.DoctorDtoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorDtoResponse createDoctor(@RequestBody DoctorDto doctorDto){
        return doctorService.createDoctor(doctorDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorDtoResponse findById(@PathVariable Integer id){
        return doctorService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorDtoResponse> findAll(){
        return doctorService.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id){
        doctorService.deleteById(id);
    }
}
