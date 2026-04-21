package com.appointments.appointments.auth;

import com.appointments.appointments.appUser.AppUser;
import com.appointments.appointments.appUser.AppUserService;
import com.appointments.appointments.auth.dto.AuthCoordinatorRequest;
import com.appointments.appointments.auth.dto.AuthDoctorRequest;
import com.appointments.appointments.coordinator.Coordinator;
import com.appointments.appointments.coordinator.CoordinatorMapper;
import com.appointments.appointments.coordinator.dto.CoordinatorResponse;
import com.appointments.appointments.coordinator.CoordinatorService;
import com.appointments.appointments.doctor.Doctor;
import com.appointments.appointments.doctor.DoctorMapper;
import com.appointments.appointments.doctor.DoctorService;
import com.appointments.appointments.doctor.dto.DoctorResponse;
import com.appointments.appointments.doctorSpecialty.DoctorSpecialtyService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AppUserService appUserService;
    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final DoctorSpecialtyService doctorSpecialtyService;
    private final CoordinatorService coordinatorService;
    private final CoordinatorMapper coordinatorMapper;

    public AuthService(AppUserService appUserService, DoctorService doctorService, DoctorMapper doctorMapper, DoctorSpecialtyService doctorSpecialtyService, CoordinatorService coordinatorService, CoordinatorMapper coordinatorMapper) {
        this.appUserService = appUserService;
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
        this.doctorSpecialtyService = doctorSpecialtyService;
        this.coordinatorService = coordinatorService;
        this.coordinatorMapper = coordinatorMapper;
    }

    @Transactional
    public DoctorResponse registerDoctor(AuthDoctorRequest authDoctorRequest){
        AppUser appUser = new AppUser();
        appUser.setEmail(authDoctorRequest.email());
        appUser.setPassword(authDoctorRequest.password());
        appUser.setRole(authDoctorRequest.role());

        appUser = appUserService.createAppUserEntity(appUser);

        Doctor doctor = new Doctor();
        doctor.setName(authDoctorRequest.name());
        doctor.setDoctorSpecialty(doctorSpecialtyService.findByIdEntity(authDoctorRequest.specialtyId()));
        doctor.setAppUser(appUser);

        doctor = doctorService.createDoctorEntity(doctor);

        return doctorMapper.toDoctorDtoResponse(doctor);
    }

    @Transactional
    public CoordinatorResponse registerCoordinator(AuthCoordinatorRequest authCoordinatorRequest){
        AppUser appUser = new AppUser();
        appUser.setEmail(authCoordinatorRequest.email());
        appUser.setPassword(authCoordinatorRequest.password());
        appUser.setRole(authCoordinatorRequest.role());

        appUser = appUserService.createAppUserEntity(appUser);

        Coordinator coordinator = new Coordinator();
        coordinator.setName(authCoordinatorRequest.name());
        coordinator.setAppUser(appUser);

        coordinator = coordinatorService.createCoordinatorEntity(coordinator);

        return coordinatorMapper.toCoordinatorResponse(coordinator);
    }
}
