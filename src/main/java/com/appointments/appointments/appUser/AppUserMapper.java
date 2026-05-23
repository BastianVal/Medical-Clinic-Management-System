package com.appointments.appointments.appUser;

import com.appointments.appointments.appUser.dto.AppUserRequest;
import com.appointments.appointments.appUser.dto.AppUserResponse;
import org.springframework.stereotype.Service;

@Service
public class AppUserMapper {
    public AppUser toAppUser(AppUserRequest dto){
        AppUser appUser =  new AppUser();
        appUser.setEmail(dto.email());
        appUser.setPassword(dto.password());
        appUser.setIsActive(true);
        appUser.setRole(dto.role());

        return appUser;
    }

    public AppUserResponse toAppUserResponse(AppUser appUser){
        Integer profileId = appUser.getDoctor() != null ? appUser.getDoctor().getId() : appUser.getCoordinator().getId();

        return new AppUserResponse(
                appUser.getId(),
                appUser.getEmail(),
                appUser.getRole().name(),
                appUser.getIsActive(),
                profileId
        );
    }
}
