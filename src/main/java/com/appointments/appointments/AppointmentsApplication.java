package com.appointments.appointments;

import com.appointments.appointments.appointmentStatus.AppointmentStatusEnum;
import com.appointments.appointments.appointmentStatus.AppointmentStatus;
import com.appointments.appointments.appointmentStatus.AppointmentStatusRepository;
import com.appointments.appointments.auth.AuthService;
import com.appointments.appointments.auth.dto.AuthCoordinatorRequest;
import com.appointments.appointments.auth.dto.AuthDoctorRequest;
import com.appointments.appointments.auth.role.Role;
import com.appointments.appointments.doctorSpecialty.DoctorSpecialty;
import com.appointments.appointments.doctorSpecialty.DoctorSpecialtyEnum;
import com.appointments.appointments.doctorSpecialty.DoctorSpecialtyRepository;
import com.appointments.appointments.roomStatus.RoomStatus;
import com.appointments.appointments.roomStatus.RoomStatusEnum;
import com.appointments.appointments.roomStatus.RoomStatusRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.ArrayList;

@SpringBootApplication
public class AppointmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentsApplication.class, args);
	}


	@Bean
	@Transactional
	CommandLineRunner initDatabase(AppointmentStatusRepository appointmentStatusRepository,
								   DoctorSpecialtyRepository doctorSpecialtyRepository,
								   RoomStatusRepository roomStatusRepository,
								   AuthService authService
	) {
		return args -> {
			if (appointmentStatusRepository.count() == 0) { // Solo si la tabla está vacía
				appointmentStatusRepository.save(new AppointmentStatus(1, AppointmentStatusEnum.ACTIVE, new ArrayList<>()));
				appointmentStatusRepository.save(new AppointmentStatus(2, AppointmentStatusEnum.CANCELED, new ArrayList<>()));
				appointmentStatusRepository.save(new AppointmentStatus(3, AppointmentStatusEnum.EXPIRED, new ArrayList<>()));
			}
			if (doctorSpecialtyRepository.count() == 0) { // Solo si la tabla está vacía
				doctorSpecialtyRepository.save(new DoctorSpecialty(1, DoctorSpecialtyEnum.DOCTOR, new ArrayList<>()));
				doctorSpecialtyRepository.save(new DoctorSpecialty(2, DoctorSpecialtyEnum.TERAPHIST, new ArrayList<>()));
				doctorSpecialtyRepository.save(new DoctorSpecialty(3, DoctorSpecialtyEnum.DENTIST, new ArrayList<>()));
			}
			if (roomStatusRepository.count() == 0) { // Solo si la tabla está vacía
				roomStatusRepository.save(new RoomStatus(1, RoomStatusEnum.UNOCCUPIED, new ArrayList<>()));
				roomStatusRepository.save(new RoomStatus(2, RoomStatusEnum.OCCUPIED, new ArrayList<>()));

				authService.registerCoordinator(new AuthCoordinatorRequest(
						"coordinator1@example.com",
						"coordinator",
						Role.ROLE_COORDINATOR,
						"my name is coordinator1"
				));

				authService.registerDoctor(new AuthDoctorRequest(
						"doctor1@example.com",
						"doctor",
						Role.ROLE_DOCTOR,
						"my name is doctor1",
						1
				));
			}
		};
	}
}
