package com.appointments.appointments.room;

import com.appointments.appointments.room.roomDtos.RoomDto;
import com.appointments.appointments.room.roomDtos.RoomDtoResponse;
import org.springframework.http.HttpStatus;
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
    public RoomDtoResponse createRoom(@RequestBody RoomDto roomDto){
        return roomService.createRoom(roomDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomDtoResponse findById(@PathVariable Integer id){
        return roomService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoomDtoResponse> findAll(){
        return roomService.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id){
        roomService.deleteById(id);
    }
}
