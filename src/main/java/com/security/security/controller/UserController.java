package com.security.security.controller;

import com.security.security.Service.ServiceImpl.UserServiceImpl;
import com.security.security.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("signup_user")
    public ResponseEntity signup_user(UserVO userVO){
        System.out.println(userVO);
        return userService.signup_user(userVO);
    }
}
