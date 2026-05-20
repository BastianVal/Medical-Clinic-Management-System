package com.appointments.appointments;

import com.appointments.appointments.appointmentStatus.AppointmentStatusEnum;
import com.appointments.appointments.appointmentStatus.AppointmentStatus;
import com.appointments.appointments.appointmentStatus.AppointmentStatusRepository;
import com.appointments.appointments.auth.AuthService;
import com.appointments.appointments.auth.dto.AuthCoordinatorRequest;
import com.appointments.appointments.auth.role.Role;
import com.appointments.appointments.coordinator.CoordinatorRepository;
import com.appointments.appointments.doctor.DoctorRepository;
import com.appointments.appointments.doctorSpecialty.DoctorSpecialtyRepository;
import com.appointments.appointments.doctorSpecialty.DoctorSpecialtyService;
import com.appointments.appointments.doctorSpecialty.dto.DoctorSpecialtyRequest;
import com.appointments.appointments.roomStatus.RoomStatus;
import com.appointments.appointments.roomStatus.RoomStatusEnum;
import com.appointments.appointments.roomStatus.RoomStatusRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import java.util.ArrayList;

@SpringBootApplication
@EnableCaching
public class AppointmentsApplication {
	@Value("${COORDINATOR_EMAIL}")
	private String coordinatorEmail;
	@Value("${COORDINATOR_PASSWORD}")
	private String coordinatorPassword;

	public static void main(String[] args) {
		SpringApplication.run(AppointmentsApplication.class, args);
	}


	@Bean
	@Transactional
	CommandLineRunner initDatabase(AppointmentStatusRepository appointmentStatusRepository,
								   DoctorSpecialtyRepository doctorSpecialtyRepository,
								   RoomStatusRepository roomStatusRepository,
								   DoctorRepository doctorRepository,
								   CoordinatorRepository coordinatorRepository,
								   AuthService authService,
								   DoctorSpecialtyService doctorSpecialtyService
	) {
		return args -> {
			if (appointmentStatusRepository.count() == 0) { // Solo si la tabla está vacía
				appointmentStatusRepository.save(new AppointmentStatus(1, AppointmentStatusEnum.ACTIVE, new ArrayList<>()));
				appointmentStatusRepository.save(new AppointmentStatus(2, AppointmentStatusEnum.CANCELED, new ArrayList<>()));
				appointmentStatusRepository.save(new AppointmentStatus(3, AppointmentStatusEnum.EXPIRED, new ArrayList<>()));
			}
			if (doctorSpecialtyRepository.count() == 0) {
				doctorSpecialtyService.createSpecialty(new DoctorSpecialtyRequest("DOCTOR"));
				doctorSpecialtyService.createSpecialty(new DoctorSpecialtyRequest("THERAPIST"));
				doctorSpecialtyService.createSpecialty(new DoctorSpecialtyRequest("DENTIST"));
			}
			if (roomStatusRepository.count() == 0) {
				roomStatusRepository.save(new RoomStatus(1, RoomStatusEnum.UNOCCUPIED, new ArrayList<>()));
				roomStatusRepository.save(new RoomStatus(2, RoomStatusEnum.OCCUPIED, new ArrayList<>()));
			}
			if(coordinatorRepository.count() == 0){
				authService.registerCoordinator(new AuthCoordinatorRequest(
						coordinatorEmail,
						coordinatorPassword,
						Role.ROLE_COORDINATOR,
						"my name is coordinator1"
				));
			}
		};
	}

	@Bean
	public CacheManager cacheManager() {
		// Si alguien intenta usar un caché que no está en esta lista, la app explota rápido
		return new ConcurrentMapCacheManager(
				"specialtiesCache",
				"doctorsCache",
				"patientsCache",
				"roomsCache"
		);
	}
}
