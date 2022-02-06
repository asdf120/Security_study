package com.security.security.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
public class User{

    private String userId;
    private String userSocialKey;
    private String userEmail;
    private String userPw;
    private String userName;
    private String userPhone;
    private String userRole;
    private String userCreateDate;

    private String provider;
}
