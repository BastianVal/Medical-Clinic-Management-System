package com.appointments.appointments.room;

import com.appointments.appointments.appoinment.Appointment;
import com.appointments.appointments.roomStatus.RoomStatus;
import com.appointments.appointments.roomStatus.RoomStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer number;

    @ManyToOne
    @JoinColumn(name = "status_id")
    @ColumnDefault("1")
    private RoomStatus roomStatus;

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    private List<Appointment> appointments;
}
