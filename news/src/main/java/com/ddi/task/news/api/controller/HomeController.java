package com.ddi.task.news.api.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/user/signup")
    public String userSignup() {

        return "user/signup";
    }

    @GetMapping("/user/login")
    public String userLogin() {

        return "user/login";
    }

    @GetMapping("/logout")
    public String userLogin(HttpSession session) {
        session.invalidate();

        return "user/login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {

        return "dashboard/dashboard";
    }

    @GetMapping("/setting/keyword")
    public String settingKeyword() {

        return "setting/keyword";
    }

    @GetMapping("/setting/site")
    public String settingSite() {

        return "setting/site";
    }

}
