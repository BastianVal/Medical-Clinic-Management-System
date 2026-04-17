package com.appointments.appointments.room;

import com.appointments.appointments.room.roomDtos.RoomDto;
import com.appointments.appointments.room.roomDtos.RoomDtoResponse;
import com.appointments.appointments.roomStatus.RoomStatus;
import com.appointments.appointments.roomStatus.RoomStatusEnum;
import com.appointments.appointments.roomStatus.RoomStatusRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomStatusRepository roomStatusRepository;
    private final RoomMapper roomMapper;

    public RoomService(RoomRepository roomRepository, RoomStatusRepository roomStatusRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomStatusRepository = roomStatusRepository;
        this.roomMapper = roomMapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    public RoomDtoResponse createRoom(RoomDto dto){
        RoomStatus roomStatus = roomStatusRepository.findById(dto.roomStatusId()).orElse(new RoomStatus());
        roomStatus.setStatus(RoomStatusEnum.UNOCCUPIED);

        Room room = roomMapper.toRoom(dto, roomStatus);

        roomRepository.save(room);

        return roomMapper.toRoomDtoResponse(room);
    }

    @ResponseStatus(HttpStatus.OK)
    public RoomDtoResponse findById(Integer id){
        Room room = roomRepository.findById(id).orElse(new Room());

        return roomMapper.toRoomDtoResponse(room);
    }

    @ResponseStatus(HttpStatus.OK)
    public List<RoomDtoResponse> findAll(){
        return  roomRepository.findAll()
                .stream()
                .map(roomMapper::toRoomDtoResponse)
                .toList();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(Integer id){
        roomRepository.deleteById(id);
    }
}
