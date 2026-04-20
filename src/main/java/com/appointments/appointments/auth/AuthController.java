package com.appointments.appointments.auth;

import com.appointments.appointments.auth.dto.AuthCoordinatorRequest;
import com.appointments.appointments.auth.dto.AuthDoctorRequest;
import com.appointments.appointments.coordinator.dto.CoordinatorResponse;
import com.appointments.appointments.doctor.dto.DoctorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("doctor")
    public DoctorResponse registerDoctor(@RequestBody AuthDoctorRequest authDoctorRequest){
        return authService.registerDoctor(authDoctorRequest);
    }

    @PostMapping("coordinator")
    public CoordinatorResponse registerCoordinator(@RequestBody AuthCoordinatorRequest authCoordinatorRequest){
        return authService.registerCoordinator(authCoordinatorRequest);
    }
}
