package com.appointments.appointments.doctor;

import com.appointments.appointments.appUser.AppUser;
import com.appointments.appointments.appoinment.Appointment;
import com.appointments.appointments.doctorSpecialty.DoctorSpecialty;
import com.appointments.appointments.doctorSpecialty.DoctorSpecialtyEnum;
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
public class Doctor {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private DoctorSpecialty doctorSpecialty;

    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<Appointment> appointments;

    @OneToOne
    @JoinColumn(name = "account_id")
    private AppUser appUser;
}
