package org.example.security;

import lombok.Data;
import org.example.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
public class SecurityUser implements UserDetails {

    private String username;
    private String password;
    private Set<SimpleGrantedAuthority> authorities;
    private Long id;
    private boolean flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SecurityUser(String username, String password, Set<SimpleGrantedAuthority> authorities, boolean flag) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.id=id;
        this.flag = flag;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return flag;
    }

    @Override
    public boolean isAccountNonLocked() {
        return flag;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return flag;
    }

    @Override
    public boolean isEnabled() {
        return flag;
    }

    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRole().getAuthorities()

        );
    }
}
