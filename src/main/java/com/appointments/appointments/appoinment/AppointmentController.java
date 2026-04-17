package com.appointments.appointments.appoinment;

import com.appointments.appointments.appoinment.appointmentDtos.AppointmentDTO;
import com.appointments.appointments.appoinment.appointmentDtos.AppointmentDtoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDtoResponse createAppointment(@RequestBody AppointmentDTO dto){
        return appointmentService.createAppointment(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentDtoResponse findById(@PathVariable Integer id){
        return appointmentService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDtoResponse> findAll(){
        return appointmentService.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id){
        appointmentService.deleteById(id);
    }


}
