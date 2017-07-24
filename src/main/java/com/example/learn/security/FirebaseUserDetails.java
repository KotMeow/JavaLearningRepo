package com.example.learn.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class FirebaseUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    private final boolean enabled = true;
    private final boolean credentialsNonExpired = true;
    private final boolean accountNonLocked = true;
    private final boolean accountNonExpired = true;
    private final String password = null;
    private final String username;
    private final String id;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
