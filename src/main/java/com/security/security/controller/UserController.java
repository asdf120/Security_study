package com.security.security.controller;

import com.security.security.Service.ServiceImpl.UserServiceImpl;
import com.security.security.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("social-success")
    public ResponseEntity social_success(Authentication authentication){
        System.out.println("user controller 26line 소셜 로그인: " + authentication.getName());
        return null;
    }
}
