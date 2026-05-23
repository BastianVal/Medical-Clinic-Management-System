package com.appointments.appointments.doctor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    List<Doctor> findByAppUser_IsActiveTrue();
}
