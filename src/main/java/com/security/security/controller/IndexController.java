package com.security.security.controller;

import com.security.security.config.auth.PrincipalDetails;
import com.security.security.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public ResponseEntity index(@AuthenticationPrincipal User user){
        System.out.println("controller index 19line : " + user);
        return new ResponseEntity("오케", HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity login(){
        return new ResponseEntity("로그인", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity user(){

        return new ResponseEntity("user", HttpStatus.OK);
    }

    @GetMapping("/join")
    public ResponseEntity join(){
        return new ResponseEntity("join", HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public ResponseEntity info(){
        return new ResponseEntity("개인정보", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/data")
    public ResponseEntity data(){
        return new ResponseEntity("data 정보", HttpStatus.OK);
    }
}
