package com.security.security.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
public class User implements UserDetails {

    private String userId;
    private String userSocialKey;
    private String userEmail;
    private String userPw;
    private String userName;
    private String userPhone;
    private String userRole;
    private String userCreateDate;

    private String provider;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userRole;
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return userPw;
    }

    @Override
    public String getUsername() {
        return userEmail;
    }

    // 계정만료
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠김
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
        // 1년동안 회원이 로그인을 안하여 휴면 계정 전환시 false
        return true;
    }
}
