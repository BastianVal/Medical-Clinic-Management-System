package com.appointments.appointments.doctor;

import com.appointments.appointments.appUser.AppUser;
import com.appointments.appointments.doctor.dto.DoctorRequest;
import com.appointments.appointments.doctor.dto.DoctorResponse;
import com.appointments.appointments.doctorSpecialty.DoctorSpecialty;
import org.springframework.stereotype.Service;

@Service
public class DoctorMapper {
    public Doctor toDoctor(DoctorRequest dto, DoctorSpecialty doctorSpecialty, AppUser appUser){
        Doctor doctor =  new Doctor();
        doctor.setName(dto.name());
        doctor.setDoctorSpecialty(doctorSpecialty);
        doctor.setAppUser(appUser);

        return doctor;
    }

    public DoctorResponse toDoctorDtoResponse(Doctor doctor){
        return new DoctorResponse(
                doctor.getId(),
                doctor.getName(),
                doctor.getAppUser().getEmail(),
                doctor.getDoctorSpecialty().getId(),
                doctor.getDoctorSpecialty().getSpecialty(),
                doctor.getAppUser().getId(),
                doctor.getAppUser().getIsActive()
        );
    }
}
