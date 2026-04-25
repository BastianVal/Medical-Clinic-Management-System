package com.appointments.appointments.patient;

import com.appointments.appointments.appoinment.Appointment;
import com.appointments.appointments.doctor.Doctor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Appointment> appointments;

    @ManyToMany(mappedBy = "patients")
    private Set<Doctor> doctors = new HashSet<>();
}
