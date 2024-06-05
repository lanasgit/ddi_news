package com.ddi.task.news.api.service;

import com.ddi.task.news.api.dto.LoginReqDto;
import com.ddi.task.news.api.dto.SignupReqDto;
import com.ddi.task.news.api.mapper.UserMapper;
import com.ddi.task.news.api.vo.UserVo;
import com.ddi.task.news.common.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Boolean emailExist(String email) {
        Boolean result = false;
        try {
            Integer count = userMapper.countByEmail(email);
            if (count > 0) result = true;

            return result;
        } catch (Exception e) {
            throw new CustomException("emailExist fail");
        }
    }

    @Transactional
    public Boolean signup(SignupReqDto signupReqDto) {
        try {
            signupReqDto.setPassword(passwordEncoder.encode(signupReqDto.getPassword()));
            userMapper.insertUser(signupReqDto);
            return true;
        } catch (Exception e) {
            throw new CustomException("signup fail");
        }
    }

    @Transactional(readOnly = true)
    public Boolean login(HttpServletRequest request, LoginReqDto loginReqDto) {
        try {
            UserVo user = userMapper.findUserByEmail(loginReqDto)
                    .orElseThrow(() -> new CustomException("존재하지 않는 계정입니다."));
            String dbPassword = user.getPassword();
            if (!passwordEncoder.matches(loginReqDto.getPassword(), dbPassword)) {
                throw new CustomException("비밀번호가 다릅니다.");
            }

            HttpSession session = request.getSession();
            session.setAttribute("u_sn", user.getUserSn());
            session.setAttribute("u_name", user.getName());
            session.setMaxInactiveInterval(60 * 60);

            return true;
        } catch (Exception e) {
            throw new CustomException("login fail");
        }
    }

}
