package com.appointments.appointments.appUser;

import com.appointments.appointments.appUser.dto.AppUserRequestChangePassword;
import com.appointments.appointments.appUser.dto.AppUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    public AppUserService(AppUserRepository appUserRepository, AppUserMapper appUserMapper) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
    }

    // =========================================================================
    // METHODS FOR THE CONTROLLERS (Retrieves DTOs)
    // =========================================================================

    public AppUserResponse findAppUserById(Integer id){
        AppUser appUser = appUserRepository.findById(id).orElse(new AppUser());

        return appUserMapper.toAppUserResponse(appUser);
    }

    public List<AppUserResponse> findAllAppUser(){
        return appUserRepository.findAll()
                .stream()
                .map(appUserMapper::toAppUserResponse)
                .toList();
    }

    public void changePassword(Integer id, AppUserRequestChangePassword dto){
        AppUser appUser = appUserRepository.findById(id).orElse(new AppUser());

        if(!dto.oldPassword().equals(appUser.getPassword())){
            throw new IllegalArgumentException("Incorrect Password");
        }
        appUser.setPassword(dto.newPassword());

        appUserRepository.save(appUser);
    }

    // =========================================================================
    // METHODS FOR THE SERVICES (Retrieves Entities)
    // =========================================================================

    public AppUser createAppUserEntity(AppUser appUser){
        return appUserRepository.save(appUser);
    }

    public AppUser findAppUserByIdEntity(Integer id){
        return appUserRepository.findById(id).orElse(new AppUser());
    }
}
