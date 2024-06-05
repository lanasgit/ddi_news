package com.ddi.task.news.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        Long u_sn = (Long) session.getAttribute("u_sn");

        if (u_sn == null) {
            response.sendRedirect("/user/login");
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
