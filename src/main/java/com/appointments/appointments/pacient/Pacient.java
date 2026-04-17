package com.appointments.appointments.pacient;

import com.appointments.appointments.appoinment.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pacient {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "pacient")
    @JsonIgnore
    private List<Appointment> appointments;
}
