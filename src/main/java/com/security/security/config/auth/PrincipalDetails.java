package com.security.security.config.auth;

// 시큐리티가 /login을 주소 요청이 오면 낚아채서 로그인을 진행
// 로그인을 진행이 완료가 되면 시큐리티 session을 만들어줌. (Security ContextHolder)
// 오브젝트 => Authentication 타입의 객체
// Authentication 안에 User 정보가 있어야 됨.
// User오브젝트타입 => UserDetails 타입 객체

// Security Session => Authentication => UserDetails(PrincipalDetails)

import com.security.security.vo.UserVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private UserVO userVO; // 컴포지션

    public PrincipalDetails(UserVO userVO){
        this.userVO = userVO;
    }

    // 해당 유저의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> collection = new ArrayList<>();
//        collection.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return null;
//            }
//        });
        return null;
    }

    @Override
    public String getPassword() {
        return userVO.getUser_pw();
    }

    @Override
    public String getUsername() {
        return userVO.getUser_id();
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
