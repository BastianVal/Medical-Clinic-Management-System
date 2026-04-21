package com.appointments.appointments.room;

import com.appointments.appointments.room.dto.RoomRequest;
import com.appointments.appointments.room.dto.RoomResponse;
import com.appointments.appointments.roomStatus.RoomStatus;
import com.appointments.appointments.roomStatus.RoomStatusEnum;
import com.appointments.appointments.roomStatus.RoomStatusRepository;
import com.appointments.appointments.roomStatus.RoomStatusService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomStatusService roomStatusService;
    private final RoomMapper roomMapper;

    public RoomService(RoomRepository roomRepository, RoomStatusService roomStatusService, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomStatusService = roomStatusService;
        this.roomMapper = roomMapper;
    }

    // =========================================================================
    // METHODS FOR THE CONTROLLERS (Retrieves DTOs)
    // =========================================================================

    public RoomResponse createRoom(RoomRequest dto){
        RoomStatus roomStatus = roomStatusService.findByIdEntity(dto.roomStatusId());

        roomStatus.setStatus(RoomStatusEnum.UNOCCUPIED);

        Room room = roomMapper.toRoom(dto, roomStatus);

        roomRepository.save(room);

        return roomMapper.toRoomDtoResponse(room);
    }

    public RoomResponse findById(Integer id){
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room Not Found"));

        return roomMapper.toRoomDtoResponse(room);
    }

    public List<RoomResponse> findAll(){
        return  roomRepository.findAll()
                .stream()
                .map(roomMapper::toRoomDtoResponse)
                .toList();
    }

    public RoomResponse updateRoom(Integer id, RoomRequest dto){
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room Not Found"));

        room.setNumber(dto.number());

        room = roomRepository.save(room);

        return roomMapper.toRoomDtoResponse(room);
    }

    public void deleteById(Integer id){
        if(!roomRepository.existsById(id)) throw new EntityNotFoundException("Room Not Found");

        roomRepository.deleteById(id);
    }

    // =========================================================================
    // METHODS FOR THE SERVICES (Retrieves Entities)
    // =========================================================================

    public Room findByIdEntity(Integer id){
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room Not Found"));
    }
}
