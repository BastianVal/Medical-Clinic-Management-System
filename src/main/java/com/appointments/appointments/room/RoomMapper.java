package com.appointments.appointments.room;

import com.appointments.appointments.room.dto.RoomRequest;
import com.appointments.appointments.room.dto.RoomResponse;
import com.appointments.appointments.roomStatus.RoomStatus;
import org.springframework.stereotype.Service;

@Service
public class RoomMapper {
    public Room toRoom(RoomRequest dto, RoomStatus roomStatus){
        Room room =  new Room();

        room.setNumber(dto.number());
        room.setRoomStatus(roomStatus);

        return room;
    }

    public RoomResponse toRoomDtoResponse(Room room){
        return new RoomResponse(
                room.getId(),
                room.getNumber(),
                room.getRoomStatus().getStatus()
        );
    }
}
