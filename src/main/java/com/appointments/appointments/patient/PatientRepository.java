package com.appointments.appointments.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Query("SELECT p FROM Patient p JOIN p.doctors d " +
            "WHERE d.id = :doctorId " +
            "AND LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Patient> findByDoctorsPatient (@Param("name") String name, @Param("doctorId") Integer doctorId);
}
