package com.security.security.mapper;

import com.security.security.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int signUpUser(User user) throws Exception;

    User checkUserId(String userEmail) throws Exception;
}
