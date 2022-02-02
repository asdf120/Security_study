package com.security.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("manager")
public class MangerController {

    @GetMapping("")
    public ResponseEntity manager(){
        return new ResponseEntity("manager", HttpStatus.OK);
    }
}
