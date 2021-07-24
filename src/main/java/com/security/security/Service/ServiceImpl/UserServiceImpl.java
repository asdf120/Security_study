package com.security.security.Service.ServiceImpl;

import com.security.security.Service.UserService;
import com.security.security.mapper.UserMapper;
import com.security.security.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity signup_user(UserVO userVO) {
        Map<String,Object> dataMap = new HashMap<>();
        int result = 0;
        try {
            userVO.setUser_pw(passwordEncoder.encode(userVO.getUser_pw()));
            result = userMapper.signup_user(userVO);
            dataMap.put("result", result);
        }catch (Exception e){
            dataMap.put("result", result);
            System.out.println("회원가입 실패 :  " + e.toString());
        }
        return new ResponseEntity(dataMap, HttpStatus.OK);
    }
}
