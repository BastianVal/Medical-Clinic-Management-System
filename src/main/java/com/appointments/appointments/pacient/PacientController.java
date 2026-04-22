package com.appointments.appointments.pacient;

import com.appointments.appointments.appoinment.AppointmentService;
import com.appointments.appointments.appoinment.dto.AppointmentResponse;
import com.appointments.appointments.appointmentStatus.AppointmentStatusEnum;
import com.appointments.appointments.pacient.dto.PacientRequest;
import com.appointments.appointments.pacient.dto.PacientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pacient")
public class PacientController {
    private final PacientService pacientService;
    private final AppointmentService appointmentService;

    public PacientController(PacientService pacientService, AppointmentService appointmentService) {
        this.pacientService = pacientService;
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('COORDINATOR')")
    public PacientResponse createPacient(@RequestBody PacientRequest pacientRequest){
        return pacientService.createPacient(pacientRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PacientResponse findById(@PathVariable Integer id){
        return pacientService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public List<PacientResponse> findAll(){
        return pacientService.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PacientResponse updatePacient(@PathVariable Integer id, @RequestBody PacientRequest pacientRequest){
        return pacientService.updatePacient(id, pacientRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('COORDINATOR')")
    public void deleteById(@PathVariable Integer id){
        pacientService.deleteById(id);
    }

    @GetMapping("{id}/appointment")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public List<AppointmentResponse> findAppointmentByIdAndStatus(@PathVariable Integer id,
                                                                  @RequestParam AppointmentStatusEnum status){
        return appointmentService.findByPacientIdAndStatus(id, status);
    }
}
