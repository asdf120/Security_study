package com.security.security.Service.ServiceImpl;

import com.security.security.Service.UserService;
import com.security.security.entity.User;
import com.security.security.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity signUpUser(User user) {
        Map<String, Object> dataMap = new HashMap<>();
        int result = 0;
        try {
            user.setUserRole("ROLE_USER");
            user.setUserPw(passwordEncoder.encode(user.getUserPw()));
            result = userMapper.signUpUser(user);
            dataMap.put("result", result);
        } catch (Exception e) {
            dataMap.put("result", result);
            System.out.println("회원가입 실패 :  " + e.toString());
        }
        return new ResponseEntity(dataMap, HttpStatus.OK);
    }
}
