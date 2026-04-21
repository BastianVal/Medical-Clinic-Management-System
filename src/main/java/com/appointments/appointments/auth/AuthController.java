package com.appointments.appointments.auth;

import com.appointments.appointments.auth.dto.AuthCoordinatorRequest;
import com.appointments.appointments.auth.dto.AuthDoctorRequest;
import com.appointments.appointments.coordinator.dto.CoordinatorResponse;
import com.appointments.appointments.doctor.dto.DoctorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("doctor")
    @ResponseStatus(HttpStatus.CREATED)
    public DoctorResponse registerDoctor(@RequestBody AuthDoctorRequest authDoctorRequest){
        return authService.registerDoctor(authDoctorRequest);
    }

    @PostMapping("coordinator")
    @ResponseStatus(HttpStatus.CREATED)
    public CoordinatorResponse registerCoordinator(@RequestBody AuthCoordinatorRequest authCoordinatorRequest){
        return authService.registerCoordinator(authCoordinatorRequest);
    }
}
