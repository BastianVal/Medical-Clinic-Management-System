package com.appointments.appointments.appUser;

import com.appointments.appointments.appUser.dto.AppUserRequestChangePassword;
import com.appointments.appointments.appUser.dto.AppUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppUserResponse findAppUserById(@PathVariable Integer id){
        return appUserService.findAppUserById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AppUserResponse> findAllAppUser(){
        return appUserService.findAllAppUser();
    }

    @PatchMapping("me/changePassword")
    public ResponseEntity<Map<String, String>> changePassword(Authentication authentication,
                                                              @RequestBody AppUserRequestChangePassword appUserRequestChangePassword){
        appUserService.changePassword(authentication.getName(), appUserRequestChangePassword);

        return ResponseEntity.ok(Map.of("message", "Password Updated Succesfully"));
    }
}
