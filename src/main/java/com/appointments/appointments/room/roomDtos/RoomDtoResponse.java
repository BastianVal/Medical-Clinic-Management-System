package com.appointments.appointments.room.roomDtos;

import com.appointments.appointments.roomStatus.RoomStatusEnum;

public record RoomDtoResponse(
        Integer number,
        RoomStatusEnum status
) {
}
