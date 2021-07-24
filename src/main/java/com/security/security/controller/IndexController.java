package com.security.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public ResponseEntity index(){
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
        return null;
    }

    @GetMapping("joinProc")
    public ResponseEntity joinProc(){
        return new ResponseEntity("회원가입 완료", HttpStatus.OK);
    }
}
