package com.appointments.appointments.appoinment;

import com.appointments.appointments.appointmentStatus.AppointmentStatus;
import com.appointments.appointments.doctor.Doctor;
import com.appointments.appointments.patient.Patient;
import com.appointments.appointments.room.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
public class Appointment {
    @Id
    @GeneratedValue
    private Integer id;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "status_id")
    @ColumnDefault("1")
    private AppointmentStatus appointmentStatus;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
