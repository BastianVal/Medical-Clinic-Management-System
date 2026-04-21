package com.appointments.appointments.doctor;

import com.appointments.appointments.appoinment.AppointmentService;
import com.appointments.appointments.appoinment.dto.AppointmentResponse;
import com.appointments.appointments.appointmentStatus.AppointmentStatusEnum;
import com.appointments.appointments.doctor.dto.DoctorRequest;
import com.appointments.appointments.doctor.dto.DoctorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    public DoctorController(DoctorService doctorService, AppointmentService appointmentService) {
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorResponse findById(@PathVariable Integer id){
        return doctorService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DoctorResponse> findAll(){
        return doctorService.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id){
        doctorService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorResponse updateDoctor(@PathVariable Integer id, @RequestBody DoctorRequest doctorRequest){
        return doctorService.updateDoctor(id, doctorRequest);
    }

    @GetMapping("{id}/appointment")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentResponse> findAppointmentByIdAndStatus(@PathVariable Integer id,
                                                                  @RequestParam AppointmentStatusEnum status){
        return appointmentService.findByDoctorIdAndStatus(id, status);
    }
}
