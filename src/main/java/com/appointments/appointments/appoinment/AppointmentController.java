package com.appointments.appointments.appoinment;

import com.appointments.appointments.appoinment.dto.AppointmentRequest;
import com.appointments.appointments.appoinment.dto.AppointmentResponse;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
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
    @PreAuthorize("hasRole('COORDINATOR')")
    public AppointmentResponse createAppointment(@RequestBody AppointmentRequest dto){
        return appointmentService.createAppointment(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public AppointmentResponse findById(@PathVariable Integer id){
        return appointmentService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public List<AppointmentResponse> findAll(){
        return appointmentService.findAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public AppointmentResponse updateAppointment(@PathVariable Integer id ,@RequestBody AppointmentRequest appointmentRequest){
        return appointmentService.updateAppointment(id, appointmentRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('COORDINATOR')")
    public void deleteById(@PathVariable Integer id){
        appointmentService.deleteById(id);
    }

    @PatchMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public AppointmentResponse cancelAppointment(@PathVariable Integer id){
        return appointmentService.cancelAppointment(id);
    }

    @GetMapping("/coordinator/dashboard")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public List<AppointmentResponse> findByDateAndDoctors(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                          @RequestParam List<Integer> doctorIds){
        return appointmentService.findByDateAndDoctors(date, doctorIds);
    }

    @GetMapping("/doctor/dashboard")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('DOCTOR')")
    public List<AppointmentResponse> findByDateAndDoctor(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                         Authentication authentication){
        return appointmentService.findByDateAndDoctor(date, authentication.getName());
    }
}
