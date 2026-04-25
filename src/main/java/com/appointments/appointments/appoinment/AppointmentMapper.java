package com.appointments.appointments.appoinment;

import com.appointments.appointments.appoinment.dto.AppointmentRequest;
import com.appointments.appointments.appoinment.dto.AppointmentResponse;
import com.appointments.appointments.appointmentStatus.AppointmentStatus;
import com.appointments.appointments.doctor.Doctor;
import com.appointments.appointments.patient.Patient;
import com.appointments.appointments.room.Room;
import org.springframework.stereotype.Service;

@Service
public class AppointmentMapper {
    public Appointment toAppointment(AppointmentRequest dto,
                                     Patient patient,
                                     Doctor doctor,
                                     Room room,
                                     AppointmentStatus status){

        Appointment appointment =  new Appointment();
        appointment.setDateTime(dto.dateTime());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setRoom(room);
        appointment.setAppointmentStatus(status);
        return appointment;
    }

    public AppointmentResponse toAppointmentDtoResponse(Appointment appointment){
        return new AppointmentResponse(
                appointment.getId(),
                appointment.getDateTime(),
                appointment.getPatient().getId(),
                appointment.getPatient().getName(),
                appointment.getDoctor().getId(),
                appointment.getDoctor().getName(),
                appointment.getRoom().getId(),
                appointment.getRoom().getNumber(),
                appointment.getAppointmentStatus().getStatus()
        );
    }
}
