package com.appointments.appointments.appointmentStatus;

import com.appointments.appointments.appoinment.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppointmentStatus {
    @Id
    private Integer id;

    //@Column(name = "status", columnDefinition = "varchar(255) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private AppointmentStatusEnum status;

    //@ToString.Exclude
    @OneToMany(mappedBy = "appointmentStatus")
    @JsonIgnore//solve with dto
    private List<Appointment> appointments;
}
