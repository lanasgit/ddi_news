package com.ddi.task.news.api.mapper;

import com.ddi.task.news.api.dto.LoginReqDto;
import com.ddi.task.news.api.dto.SignupReqDto;
import com.ddi.task.news.api.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    int countByEmail(String email);
    void insertUser(SignupReqDto signupReqDto);
    Optional<UserVo> findUserByEmail(LoginReqDto loginReqDto);
}
