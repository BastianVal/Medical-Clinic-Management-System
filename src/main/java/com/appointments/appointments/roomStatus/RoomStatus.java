package com.appointments.appointments.roomStatus;

import com.appointments.appointments.room.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RoomStatus {
    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoomStatusEnum status;

    @OneToMany(mappedBy = "roomStatus")
    @JsonIgnore
    private List<Room> rooms;
}
