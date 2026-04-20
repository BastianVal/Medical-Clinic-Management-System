package com.appointments.appointments.room.dto;

import com.appointments.appointments.roomStatus.RoomStatusEnum;

public record RoomResponse(
        Integer id,
        Integer number,
        RoomStatusEnum status
) {
}
