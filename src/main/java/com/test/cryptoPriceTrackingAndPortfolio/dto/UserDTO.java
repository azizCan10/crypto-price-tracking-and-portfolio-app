package com.test.cryptoPriceTrackingAndPortfolio.dto;

import com.test.cryptoPriceTrackingAndPortfolio.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserDTO implements UserDetails {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private Set<Role> authorities;

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
