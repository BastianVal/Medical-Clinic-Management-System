package com.appointments.appointments.room;

import com.appointments.appointments.room.dto.RoomRequest;
import com.appointments.appointments.room.dto.RoomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('COORDINATOR')")
    public RoomResponse createRoom(@RequestBody RoomRequest roomRequest){
        return roomService.createRoom(roomRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public RoomResponse findById(@PathVariable Integer id){
        return roomService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public List<RoomResponse> findAll(){
        return roomService.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public RoomResponse updateRoom(@PathVariable Integer id, @RequestBody RoomRequest roomRequest){
        return roomService.updateRoom(id, roomRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('COORDINATOR')")
    public void deleteById(@PathVariable Integer id){
        roomService.deleteById(id);
    }
}
