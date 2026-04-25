package com.appointments.appointments.patient;

import com.appointments.appointments.appoinment.AppointmentService;
import com.appointments.appointments.appoinment.dto.AppointmentResponse;
import com.appointments.appointments.appointmentStatus.AppointmentStatusEnum;
import com.appointments.appointments.patient.dto.PatientRequest;
import com.appointments.appointments.patient.dto.PatientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    public PatientResponse createPatient(@RequestBody PatientRequest patientRequest){
        return patientService.createPacient(patientRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('COORDINATOR', 'DOCTOR')")
    public PatientResponse findById(Authentication authentication,
                                    @PathVariable Integer id){
        return patientService.findById(
                authentication.getName(),
                id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('COORDINATOR', 'DOCTOR')")
    public List<PatientResponse> findAll(Authentication authentication){
        return patientService.findAll(authentication.getName());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public PatientResponse updatePatient(@PathVariable Integer id, @RequestBody PatientRequest patientRequest){
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

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('COORDINATOR', 'DOCTOR')")
    public List<PatientResponse> searchPatientName(Authentication authentication,
                                                          @RequestParam String name){
        return patientService.searchPatientName(authentication.getName(), name);
    }
}
