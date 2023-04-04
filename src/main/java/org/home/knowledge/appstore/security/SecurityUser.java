package org.home.knowledge.appstore.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.extern.slf4j.Slf4j;

/**
 * Security user wraps around persisted UserAccount to honor UserDetailService
 * interface
 */
@Slf4j
public class SecurityUser implements UserDetails {

    private final UserAccount userAccount;

    public SecurityUser(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    @Override
    public String getUsername() {
        return userAccount.getUsername();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return userAccount.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userAccount.getRoles().stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getName()))
                .toList();
        // return Arrays.stream(user
        // .getRoles()
        // .split(","))
        // .map(SimpleGrantedAuthority::new)
        // .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        log.trace("Function hasn't been implemented, returning default value");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        log.trace("Function hasn't been implemented, returning default value");
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        log.trace("Function hasn't been implemented, returning default value");
        return true;
    }

    @Override
    public boolean isEnabled() {
        log.trace("Function hasn't been implemented, returning default value");
        return true;
    }
}
