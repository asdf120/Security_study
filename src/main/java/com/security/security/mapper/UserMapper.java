package com.security.security.mapper;

import com.security.security.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    int signup_user(UserVO userVO) throws Exception;

    UserVO check_user_id(String user_id) throws Exception;
}
