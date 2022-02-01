package com.security.security.Service;

import com.security.security.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity signUpUser(User user);
}
