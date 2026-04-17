package com.appointments.appointments.appoinment;

import com.appointments.appointments.appoinment.appointmentDtos.AppointmentDTO;
import com.appointments.appointments.appoinment.appointmentDtos.AppointmentDtoResponse;
import com.appointments.appointments.appointmentStatus.AppointmentStatus;
import com.appointments.appointments.doctor.Doctor;
import com.appointments.appointments.pacient.Pacient;
import com.appointments.appointments.room.Room;
import org.springframework.stereotype.Service;

@Service
public class AppointmentMapper {
    public Appointment toAppointment(AppointmentDTO dto, Pacient pacient, Doctor doctor, Room room, AppointmentStatus status){
        Appointment appointment =  new Appointment();
        appointment.setDateTime(dto.dateTime());
        appointment.setPacient(pacient);
        appointment.setDoctor(doctor);
        appointment.setRoom(room);
        appointment.setAppointmentStatus(status);
        return appointment;
    }

    public AppointmentDtoResponse toAppointmentDtoResponse(Appointment appointment){
        return new AppointmentDtoResponse(
                appointment.getDateTime(),
                appointment.getPacient().getName(),
                appointment.getDoctor().getName(),
                appointment.getRoom().getNumber(),
                appointment.getAppointmentStatus().getStatus()
        );
    }
}
