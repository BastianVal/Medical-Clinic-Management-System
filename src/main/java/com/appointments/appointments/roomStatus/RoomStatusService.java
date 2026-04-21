package com.appointments.appointments.roomStatus;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoomStatusService {

    private final RoomStatusRepository roomStatusRepository;

    public RoomStatusService(RoomStatusRepository roomStatusRepository) {
        this.roomStatusRepository = roomStatusRepository;
    }

    // =========================================================================
    // METHODS FOR THE CONTROLLERS (Retrieves DTOs)
    // =========================================================================

    // =========================================================================
    // METHODS FOR THE SERVICES (Retrieves Entities)
    // =========================================================================

    public RoomStatus findByIdEntity(Integer id){
        return roomStatusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("RoomStatus Not Found"));
    }
}
