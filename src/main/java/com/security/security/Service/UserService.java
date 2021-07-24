package com.security.security.Service;

import com.security.security.vo.UserVO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity signup_user(UserVO userVO);
}
