package com.appointments.appointments.doctorSpecialty;

import com.appointments.appointments.doctor.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DoctorSpecialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Enumerated(EnumType.STRING)
//    private DoctorSpecialtyEnum specialty;
    private String specialty;

    @OneToMany(mappedBy = "doctorSpecialty")
    private List<Doctor> doctors;
}
