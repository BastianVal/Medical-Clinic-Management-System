package com.appointments.appointments.doctorSpecialty;

import com.appointments.appointments.doctor.dto.DoctorRequest;
import com.appointments.appointments.doctorSpecialty.dto.DoctorSpecialtyRequest;
import com.appointments.appointments.doctorSpecialty.dto.DoctorSpecialtyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialty")
public class DoctorSpecialtyController {
    private final DoctorSpecialtyService doctorSpecialtyService;

    public DoctorSpecialtyController(DoctorSpecialtyService doctorSpecialtyService) {
        this.doctorSpecialtyService = doctorSpecialtyService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('COORDINATOR')")
    public DoctorSpecialtyResponse createSpecialty(@RequestBody DoctorSpecialtyRequest doctorSpecialtyRequest){
        return doctorSpecialtyService.createSpecialty(doctorSpecialtyRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('COORDINATOR', 'DOCTOR')")
    public List<DoctorSpecialtyResponse> findAll(){
        return doctorSpecialtyService.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public DoctorSpecialtyResponse updateSpecialty(@RequestBody DoctorSpecialtyRequest doctorSpecialtyRequest, @PathVariable Integer id){
        return doctorSpecialtyService.updateSpecialty(doctorSpecialtyRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('COORDINATOR', 'DOCTOR')")
    public void deleteSpecialty(@PathVariable Integer id){
        doctorSpecialtyService.deleteSpecialty(id);
    }

}
