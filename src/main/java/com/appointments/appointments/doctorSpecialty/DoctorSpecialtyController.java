package com.appointments.appointments.doctorSpecialty;

import com.appointments.appointments.doctorSpecialty.dto.DoctorSpecialtyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/specialty")
public class DoctorSpecialtyController {
    private final DoctorSpecialtyService doctorSpecialtyService;

    public DoctorSpecialtyController(DoctorSpecialtyService doctorSpecialtyService) {
        this.doctorSpecialtyService = doctorSpecialtyService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasAnyRole('COORDINATOR', 'DOCTOR')")
    public List<DoctorSpecialtyResponse> findAll(){
        return doctorSpecialtyService.findAll();
    }
}
