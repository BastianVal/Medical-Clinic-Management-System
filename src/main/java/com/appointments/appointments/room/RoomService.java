package com.appointments.appointments.room;

import com.appointments.appointments.room.dto.RoomRequest;
import com.appointments.appointments.room.dto.RoomResponse;
import com.appointments.appointments.roomStatus.RoomStatus;
import com.appointments.appointments.roomStatus.RoomStatusService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
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

    @CacheEvict(value = "roomsCache", allEntries = true)
    public RoomResponse createRoom(RoomRequest dto){
        RoomStatus roomStatus = roomStatusService.findByIdEntity(1);

        Room room = roomMapper.toRoom(dto, roomStatus);

        roomRepository.save(room);

        return roomMapper.toRoomDtoResponse(room);
    }

    public RoomResponse findById(Integer id){
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room Not Found"));

        return roomMapper.toRoomDtoResponse(room);
    }

    @Cacheable("roomsCache")
    public List<RoomResponse> findAll(){
        return  roomRepository.findAll()
                .stream()
                .map(roomMapper::toRoomDtoResponse)
                .toList();
    }

    @CacheEvict(value = "roomsCache", allEntries = true)
    public RoomResponse updateRoom(Integer id, RoomRequest dto){
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room Not Found"));

        room.setNumber(dto.number());

        room = roomRepository.save(room);

        return roomMapper.toRoomDtoResponse(room);
    }

    @CacheEvict(value = "roomsCache", allEntries = true)
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
