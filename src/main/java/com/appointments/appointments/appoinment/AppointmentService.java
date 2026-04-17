package com.appointments.appointments.appoinment;

import com.appointments.appointments.appoinment.appointmentDtos.AppointmentDTO;
import com.appointments.appointments.appoinment.appointmentDtos.AppointmentDtoResponse;
import com.appointments.appointments.appointmentStatus.AppointmentStatus;
import com.appointments.appointments.appointmentStatus.AppointmentStatusRepository;
import com.appointments.appointments.doctor.Doctor;
import com.appointments.appointments.doctor.DoctorRepository;
import com.appointments.appointments.pacient.Pacient;
import com.appointments.appointments.pacient.PacientRepository;
import com.appointments.appointments.room.Room;
import com.appointments.appointments.room.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PacientRepository pacientRepository;
    private final DoctorRepository doctorRepository;
    private final RoomRepository roomRepository;
    private final AppointmentStatusRepository appointmentStatusRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentService(AppointmentRepository appointmentRepository, PacientRepository pacientRepository, DoctorRepository doctorRepository, RoomRepository roomRepository, AppointmentStatusRepository appointmentStatusRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.pacientRepository = pacientRepository;
        this.doctorRepository = doctorRepository;
        this.roomRepository = roomRepository;
        this.appointmentStatusRepository = appointmentStatusRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDtoResponse createAppointment(AppointmentDTO dto){
        Pacient pacient = pacientRepository.findById(dto.pacientId()).orElse(new Pacient());
        Doctor doctor = doctorRepository.findById(dto.doctorId()).orElse(new Doctor());
        Room room = roomRepository.findById(dto.roomId()).orElse(new Room());
        AppointmentStatus status = appointmentStatusRepository.findById(1).orElse(new AppointmentStatus());

        Appointment appointment = appointmentMapper.toAppointment(dto, pacient, doctor, room, status);

        appointmentRepository.save(appointment);

        return appointmentMapper.toAppointmentDtoResponse(appointment);
    }

    @ResponseStatus(HttpStatus.OK)
    public AppointmentDtoResponse findById(Integer id){
         Appointment appointment = appointmentRepository.findById(id).orElse(new Appointment());

         return appointmentMapper.toAppointmentDtoResponse(appointment);
    }

    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDtoResponse> findAll(){
        return  appointmentRepository.findAll()
                .stream()
                .map(appointmentMapper::toAppointmentDtoResponse)
                .toList();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Integer id){
        appointmentRepository.deleteById(id);
    }
}
