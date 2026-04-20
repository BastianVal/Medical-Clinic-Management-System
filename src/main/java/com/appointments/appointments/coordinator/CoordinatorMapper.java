package com.appointments.appointments.coordinator;

import com.appointments.appointments.appUser.AppUser;
import com.appointments.appointments.coordinator.dto.CoordinatorRequest;
import com.appointments.appointments.coordinator.dto.CoordinatorResponse;
import org.springframework.stereotype.Service;

@Service
public class CoordinatorMapper {

    public Coordinator toCoordinator(CoordinatorRequest dto, AppUser appUser){
        Coordinator coordinator =  new Coordinator();
        coordinator.setAppUser(appUser);
        coordinator.setName(dto.name());

        return coordinator;
    }

    public CoordinatorResponse toCoordinatorResponse(Coordinator coordinator){
        return new CoordinatorResponse(
                coordinator.getId(),
                coordinator.getName(),
                coordinator.getAppUser().getEmail(),
                coordinator.getAppUser().getId()

        );
    }
}
