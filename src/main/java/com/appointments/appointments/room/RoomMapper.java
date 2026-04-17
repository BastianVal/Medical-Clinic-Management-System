package com.appointments.appointments.room;

import com.appointments.appointments.doctor.Doctor;
import com.appointments.appointments.doctor.doctorDtos.DoctorDto;
import com.appointments.appointments.doctor.doctorDtos.DoctorDtoResponse;
import com.appointments.appointments.doctorSpecialty.DoctorSpecialty;
import com.appointments.appointments.room.roomDtos.RoomDto;
import com.appointments.appointments.room.roomDtos.RoomDtoResponse;
import com.appointments.appointments.roomStatus.RoomStatus;
import org.springframework.stereotype.Service;

@Service
public class RoomMapper {
    public Room toRoom(RoomDto dto, RoomStatus roomStatus){
        Room room =  new Room();

        room.setNumber(dto.number());
        room.setRoomStatus(roomStatus);

        return room;
    }

    public RoomDtoResponse toRoomDtoResponse(Room room){
        return new RoomDtoResponse(
                room.getNumber(),
                room.getRoomStatus().getStatus()
        );
    }
}
