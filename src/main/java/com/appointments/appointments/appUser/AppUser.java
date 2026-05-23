package com.appointments.appointments.appUser;

import com.appointments.appointments.coordinator.Coordinator;
import com.appointments.appointments.doctor.Doctor;
import com.appointments.appointments.auth.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;

    private String email;

    private String password;

    private Boolean isActive;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "appUser")
    private Doctor doctor;

    @OneToOne(mappedBy = "appUser")
    private Coordinator coordinator;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
