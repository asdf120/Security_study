package com.security.security.controller;

import com.security.security.Service.ServiceImpl.UserServiceImpl;
import com.security.security.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/signup")
    public ResponseEntity signUpUser(User user) {
        System.out.println("controller user 26line : " + user);
        return userService.signUpUser(user);
    }

    @GetMapping("/social-success")
    public ResponseEntity socialSuccess(Authentication authentication) {
        System.out.println("user controller 26line 소셜 로그인: " + authentication.getName());

        return null;
    }
}
