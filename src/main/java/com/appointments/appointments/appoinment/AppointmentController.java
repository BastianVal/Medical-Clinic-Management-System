package com.appointments.appointments.appoinment;

import com.appointments.appointments.appoinment.dto.AppointmentRequest;
import com.appointments.appointments.appoinment.dto.AppointmentResponse;
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
    public AppointmentResponse createAppointment(@RequestBody AppointmentRequest dto){
        return appointmentService.createAppointment(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse findById(@PathVariable Integer id){
        return appointmentService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> findAll(){
        return appointmentService.findAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentResponse updateAppointment(@PathVariable Integer id ,@RequestBody AppointmentRequest appointmentRequest){
        return appointmentService.updateAppointment(id, appointmentRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id){
        appointmentService.deleteById(id);
    }

    @PatchMapping("/{id}/cancel")
    public AppointmentResponse cancelAppointment(@PathVariable Integer id){
        return appointmentService.cancelAppointment(id);
    }
}
