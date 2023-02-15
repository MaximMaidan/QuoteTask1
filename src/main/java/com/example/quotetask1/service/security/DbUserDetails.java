package com.example.quotetask1.service.security;

import com.example.quotetask1.db.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class DbUserDetails implements UserDetails {
    private User dbUser;

    public DbUserDetails(User user) {
        dbUser = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.<GrantedAuthority>singletonList(
                new SimpleGrantedAuthority("USER")
        );
    }

    @Override
    public String getPassword() {
        return dbUser.getPassword();
    }

    @Override
    public String getUsername() {
        return dbUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
