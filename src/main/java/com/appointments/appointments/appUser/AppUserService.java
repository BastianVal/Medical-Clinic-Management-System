package com.appointments.appointments.appUser;

import com.appointments.appointments.appUser.dto.AppUserRequestChangePassword;
import com.appointments.appointments.appUser.dto.AppUserResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository, AppUserMapper appUserMapper, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================================================================
    // METHODS FOR THE CONTROLLERS (Retrieves DTOs)
    // =========================================================================

    public AppUserResponse findAppUserById(Integer id){
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account Not Found"));

        return appUserMapper.toAppUserResponse(appUser);
    }

    public List<AppUserResponse> findAllAppUser(){
        return appUserRepository.findAll()
                .stream()
                .map(appUserMapper::toAppUserResponse)
                .toList();
    }

    public void changePassword(String email, AppUserRequestChangePassword dto){
        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Email Not Found"));

        if(!passwordEncoder.matches(dto.oldPassword(), appUser.getPassword())){
            throw new BadCredentialsException("Incorrect Password");
        }
        appUser.setPassword(passwordEncoder.encode(dto.newPassword()));

        appUserRepository.save(appUser);
    }

    public AppUser findByEmailEntity(String email){
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Email Not Found"));
    }

    // =========================================================================
    // METHODS FOR THE SERVICES (Retrieves Entities)
    // =========================================================================

    public AppUser createAppUserEntity(AppUser appUser){
        return appUserRepository.save(appUser);
    }

    public AppUser findAppUserByIdEntity(Integer id){
        return appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account Not Found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email Not Found"));

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(appUser.getRole().name()));

        return User.builder()
                .username(appUser.getEmail())
                .password(appUser.getPassword())
                .authorities(authorities)
                .build();
    }
}
