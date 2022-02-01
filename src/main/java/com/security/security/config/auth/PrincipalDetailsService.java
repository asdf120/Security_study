package com.security.security.config.auth;


import com.security.security.mapper.UserMapper;
import com.security.security.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessUrl("/login");
// 로그인 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    // Security session(내부 Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("service principal 23line : " + username);
        User user = new User();
        try{
            user = userMapper.checkUserId(username);
            System.out.println("service principal password 27line : " + user);
            if (user != null) {
                return new PrincipalDetails(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new PrincipalDetails(user);
    }
}
