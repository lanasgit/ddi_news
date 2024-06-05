package com.ddi.task.news.api.controller;

import com.ddi.task.news.api.dto.LoginReqDto;
import com.ddi.task.news.api.dto.SignupReqDto;
import com.ddi.task.news.api.service.UserService;
import com.ddi.task.news.common.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/email/exist")
    public Response<Boolean> emailExist(String email) {
        return Response.success(userService.emailExist(email));
    }

    @PostMapping("/signup")
    public Response<Boolean> signup(SignupReqDto signupReqDto) {
        return Response.success(userService.signup(signupReqDto));
    }

    @PostMapping("/login")
    public Response<Boolean> login(HttpServletRequest request, LoginReqDto loginReqDto) {
        return Response.success(userService.login(request, loginReqDto));
    }

}
