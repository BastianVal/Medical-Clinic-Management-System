package com.appointments.appointments.auth;

import com.appointments.appointments.appUser.AppUser;
import com.appointments.appointments.appUser.AppUserService;
import com.appointments.appointments.auth.dto.AuthCoordinatorRequest;
import com.appointments.appointments.auth.dto.AuthDoctorRequest;
import com.appointments.appointments.auth.dto.LoginRequest;
import com.appointments.appointments.auth.dto.LoginResponse;
import com.appointments.appointments.auth.role.Role;
import com.appointments.appointments.coordinator.Coordinator;
import com.appointments.appointments.coordinator.CoordinatorMapper;
import com.appointments.appointments.coordinator.dto.CoordinatorResponse;
import com.appointments.appointments.coordinator.CoordinatorService;
import com.appointments.appointments.doctor.Doctor;
import com.appointments.appointments.doctor.DoctorMapper;
import com.appointments.appointments.doctor.DoctorService;
import com.appointments.appointments.doctor.dto.DoctorResponse;
import com.appointments.appointments.doctorSpecialty.DoctorSpecialtyService;
import com.appointments.appointments.jwt.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AppUserService appUserService;
    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final DoctorSpecialtyService doctorSpecialtyService;
    private final CoordinatorService coordinatorService;
    private final CoordinatorMapper coordinatorMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AppUserService appUserService, DoctorService doctorService, DoctorMapper doctorMapper, DoctorSpecialtyService doctorSpecialtyService, CoordinatorService coordinatorService, CoordinatorMapper coordinatorMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.appUserService = appUserService;
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
        this.doctorSpecialtyService = doctorSpecialtyService;
        this.coordinatorService = coordinatorService;
        this.coordinatorMapper = coordinatorMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Transactional
    @CacheEvict(value = "doctorsCache", allEntries = true)
    public DoctorResponse registerDoctor(AuthDoctorRequest authDoctorRequest) {
        AppUser appUser = new AppUser();
        appUser.setEmail(authDoctorRequest.email());
        appUser.setPassword(passwordEncoder.encode(authDoctorRequest.password()));
//        appUser.setRole(authDoctorRequest.role());
        appUser.setRole(Role.ROLE_DOCTOR);

        appUser = appUserService.createAppUserEntity(appUser);

        Doctor doctor = new Doctor();
        doctor.setName(authDoctorRequest.name());
        doctor.setDoctorSpecialty(doctorSpecialtyService.findByIdEntity(authDoctorRequest.specialtyId()));
        doctor.setAppUser(appUser);

        doctor = doctorService.createDoctorEntity(doctor);

        return doctorMapper.toDoctorDtoResponse(doctor);
    }

    @Transactional
    public CoordinatorResponse registerCoordinator(AuthCoordinatorRequest authCoordinatorRequest) {
        AppUser appUser = new AppUser();
        appUser.setEmail(authCoordinatorRequest.email());
        appUser.setPassword(passwordEncoder.encode(authCoordinatorRequest.password()));
//        appUser.setRole(authCoordinatorRequest.role());
        appUser.setRole(Role.ROLE_COORDINATOR);

        appUser = appUserService.createAppUserEntity(appUser);

        Coordinator coordinator = new Coordinator();
        coordinator.setName(authCoordinatorRequest.name());
        coordinator.setAppUser(appUser);

        coordinator = coordinatorService.createCoordinatorEntity(coordinator);

        return coordinatorMapper.toCoordinatorResponse(coordinator);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password())
        );

        AppUser appUser = appUserService.findByEmailEntity(loginRequest.email());

        String jwtToken = jwtService.generateToken(appUser);

        if (appUser.getRole().equals(Role.ROLE_COORDINATOR)) {
            return new LoginResponse(jwtToken,
                    appUser.getId(), appUser.getEmail(), appUser.getRole().name(),
                    appUser.getCoordinator().getId(), appUser.getCoordinator().getName()
            );
        }
        else {
            return new LoginResponse(jwtToken,
                    appUser.getId(), appUser.getEmail(), appUser.getRole().name(),
                    appUser.getDoctor().getId(), appUser.getDoctor().getName()
            );
        }
    }
}
