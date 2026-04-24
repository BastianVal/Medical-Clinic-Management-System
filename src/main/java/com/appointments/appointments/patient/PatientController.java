package com.appointments.appointments.patient;

import com.appointments.appointments.appoinment.AppointmentService;
import com.appointments.appointments.appoinment.dto.AppointmentResponse;
import com.appointments.appointments.appointmentStatus.AppointmentStatusEnum;
import com.appointments.appointments.patient.dto.PatientRequest;
import com.appointments.appointments.patient.dto.PatientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;
    private final AppointmentService appointmentService;

    public PatientController(PatientService patientService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('COORDINATOR')")
    public PatientResponse createPacient(@RequestBody PatientRequest patientRequest){
        return patientService.createPacient(patientRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientResponse findById(@PathVariable Integer id){
        return patientService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public List<PatientResponse> findAll(){
        return patientService.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientResponse updatePacient(@PathVariable Integer id, @RequestBody PatientRequest patientRequest){
        return patientService.updatePacient(id, patientRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('COORDINATOR')")
    public void deleteById(@PathVariable Integer id){
        patientService.deleteById(id);
    }

    @GetMapping("{id}/appointment")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public List<AppointmentResponse> findAppointmentByIdAndStatus(@PathVariable Integer id,
                                                                  @RequestParam AppointmentStatusEnum status){
        return appointmentService.findByPatientIdAndStatus(id, status);
    }
}
