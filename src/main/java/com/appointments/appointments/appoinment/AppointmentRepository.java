package com.appointments.appointments.appoinment;

import com.appointments.appointments.appointmentStatus.AppointmentStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByDoctorIdAndAppointmentStatus_Status(Integer doctorId, AppointmentStatusEnum status);

    List<Appointment> findByPatientIdAndAppointmentStatus_Status(Integer patientId, AppointmentStatusEnum status);
}
