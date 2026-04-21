package com.appointments.appointments.appoinment;

import com.appointments.appointments.appoinment.dto.AppointmentRequest;
import com.appointments.appointments.appoinment.dto.AppointmentResponse;
import com.appointments.appointments.appointmentStatus.AppointmentStatus;
import com.appointments.appointments.appointmentStatus.AppointmentStatusEnum;
import com.appointments.appointments.appointmentStatus.AppointmentStatusService;
import com.appointments.appointments.doctor.Doctor;
import com.appointments.appointments.doctor.DoctorService;
import com.appointments.appointments.pacient.Pacient;
import com.appointments.appointments.pacient.PacientService;
import com.appointments.appointments.room.Room;
import com.appointments.appointments.room.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PacientService pacientService;
    private final DoctorService doctorService;
    private final RoomService roomService;
    private final AppointmentStatusService appointmentStatusService;
    private final AppointmentMapper appointmentMapper;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              PacientService pacientService,
                              DoctorService doctorService,
                              RoomService roomService,
                              AppointmentStatusService appointmentStatusService,
                              AppointmentMapper appointmentMapper) {

        this.appointmentRepository = appointmentRepository;
        this.pacientService = pacientService;
        this.doctorService = doctorService;
        this.roomService = roomService;
        this.appointmentStatusService = appointmentStatusService;
        this.appointmentMapper = appointmentMapper;
    }

    public AppointmentResponse createAppointment(AppointmentRequest dto){
        Pacient pacient = pacientService.findByIdEntity(dto.pacientId());
        Doctor doctor = doctorService.findByIdEntity(dto.doctorId());
        Room room = roomService.findByIdEntity(dto.roomId());
        AppointmentStatus status = appointmentStatusService.findByIdEntity(1);

        Appointment appointment = appointmentMapper.toAppointment(dto, pacient, doctor, room, status);

        appointmentRepository.save(appointment);

        return appointmentMapper.toAppointmentDtoResponse(appointment);
    }

    public AppointmentResponse findById(Integer id){
         Appointment appointment = appointmentRepository.findById(id)
                 .orElseThrow(() -> new EntityNotFoundException("Appointment Not Found"));

         return appointmentMapper.toAppointmentDtoResponse(appointment);
    }

    public List<AppointmentResponse> findAll(){
        return  appointmentRepository.findAll()
                .stream()
                .map(appointmentMapper::toAppointmentDtoResponse)
                .toList();
    }

    public AppointmentResponse updateAppointment(Integer id, AppointmentRequest dto){
        Pacient pacient = pacientService.findByIdEntity(dto.pacientId());
        Doctor doctor = doctorService.findByIdEntity(dto.doctorId());
        Room room = roomService.findByIdEntity(dto.roomId());
        AppointmentStatus status = appointmentStatusService.findById(1);

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment Not Found"));

        appointment.setDateTime(dto.dateTime());
        appointment.setPacient(pacient);
        appointment.setDoctor(doctor);
        appointment.setRoom(room);

        appointment = appointmentRepository.save(appointment);

        return appointmentMapper.toAppointmentDtoResponse(appointment);
    }

    public void deleteById(Integer id){
        if(!appointmentRepository.existsById(id)) throw new EntityNotFoundException("Appointment Not Found");
        appointmentRepository.deleteById(id);
    }

    public List<AppointmentResponse> findByDoctorIdAndStatus(Integer doctorId, AppointmentStatusEnum status){

        if(!doctorService.existById(doctorId)) {
            throw new EntityNotFoundException("Doctor Not Found");
        }

        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndAppointmentStatus_Status(doctorId, status);
        return appointments
                .stream()
                .map(appointmentMapper::toAppointmentDtoResponse)
                .toList();
    }

    public List<AppointmentResponse> findByPacientIdAndStatus(Integer pacientId, AppointmentStatusEnum status){

        if(!pacientService.existById(pacientId)) {
            throw new EntityNotFoundException("Pacient Not Found");
        }

        List<Appointment> appointments = appointmentRepository.findByPacientIdAndAppointmentStatus_Status(pacientId, status);
        return appointments
                .stream()
                .map(appointmentMapper::toAppointmentDtoResponse)
                .toList();
    }

    public AppointmentResponse cancelAppointment(Integer id){
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment Not Found"));

        AppointmentStatus appointmentStatus = appointmentStatusService.findById(2);// 2 : CANCELED

        appointment.setAppointmentStatus(appointmentStatus);

        appointment = appointmentRepository.save(appointment);

        return appointmentMapper.toAppointmentDtoResponse(appointment);
    }
}
