package com.appointments.appointments.doctor;

import com.appointments.appointments.appUser.AppUser;
import com.appointments.appointments.appoinment.Appointment;
import com.appointments.appointments.doctorSpecialty.DoctorSpecialty;
import com.appointments.appointments.patient.Patient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.SQLDelete;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
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

    @ManyToMany
    @JoinTable(
            name = "doctor_patient",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    private Set<Patient> patients = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "account_id")
    private AppUser appUser;

    public void addPatient(Patient patient){
        this.patients.add(patient);
        patient.getDoctors().add(this);
    }

    public void removePatient(Patient patient){
        this.patients.remove(patient);
        patient.getDoctors().remove(this);
    }
}
