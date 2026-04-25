package com.appointments.appointments.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Query("SELECT p FROM Patient p JOIN p.doctors d " +
            "WHERE d.id = :doctorId " +
            "AND LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Patient> searchDoctorsPatientsByName(@Param("name") String name, @Param("doctorId") Integer doctorId);

//    @Query("SELECT p FROM Patient p " +
//            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Patient> findByNameContainingIgnoreCase(String name);

    List<Patient> findAllByDoctorsId(Integer doctorId);

    @Query("SELECT p FROM Patient p JOIN p.doctors d " +
            "WHERE p.id = :patientId " +
            "AND d.id = :doctorId")
    Optional<Patient> findByDoctorsPatient(@Param("patientId") Integer id, @Param("doctorId") Integer doctorId);
}
