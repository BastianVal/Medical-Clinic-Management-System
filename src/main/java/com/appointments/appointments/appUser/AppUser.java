package com.appointments.appointments.appUser;

import com.appointments.appointments.coordinator.Coordinator;
import com.appointments.appointments.doctor.Doctor;
import com.appointments.appointments.auth.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser {
    @Id
    @GeneratedValue
    private Integer id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "appUser")
    private Doctor doctor;

    @OneToOne(mappedBy = "appUser")
    private Coordinator coordinator;
}
