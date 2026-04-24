package com.appointments.appointments.doctor;

import com.appointments.appointments.appUser.AppUserService;
import com.appointments.appointments.appoinment.AppointmentService;
import com.appointments.appointments.appoinment.dto.AppointmentResponse;
import com.appointments.appointments.appointmentStatus.AppointmentStatusEnum;
import com.appointments.appointments.doctor.dto.DoctorRequest;
import com.appointments.appointments.doctor.dto.DoctorResponse;
import com.appointments.appointments.patient.PatientService;
import com.appointments.appointments.patient.dto.PatientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final AppUserService appUserService;

    public DoctorController(DoctorService doctorService, AppointmentService appointmentService, PatientService patientService, AppUserService appUserService) {
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.appUserService = appUserService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorResponse findById(@PathVariable Integer id){
        return doctorService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public List<DoctorResponse> findAll(){
        return doctorService.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('COORDINATOR')")
    public void deleteById(@PathVariable Integer id){
        doctorService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('COORDINATOR', 'DOCTOR')")
    public DoctorResponse updateDoctor(@PathVariable Integer id, @RequestBody DoctorRequest doctorRequest){
        return doctorService.updateDoctor(id, doctorRequest);
    }

    @GetMapping("{id}/appointment")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('COORDINATOR', 'DOCTOR')")
    public List<AppointmentResponse> findAppointmentByIdAndStatus(@PathVariable Integer id,
                                                                  @RequestParam AppointmentStatusEnum status){
        return appointmentService.findByDoctorIdAndStatus(id, status);
    }

    @GetMapping("/patients")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('COORDINATOR', 'DOCTOR')")
    public List<PatientResponse> findByDoctorsPatient(@RequestParam String patientName,
                                                      Authentication authentication){
        return patientService.
                findByDoctorsPatient(
                        patientName,
                        appUserService.findByEmail(authentication.getName()).getDoctor().getId()
                );
    }
}
