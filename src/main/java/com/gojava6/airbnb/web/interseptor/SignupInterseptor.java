package com.gojava6.airbnb.web.interseptor;

import com.gojava6.airbnb.service.UserService;
import com.gojava6.airbnb.web.listene.Context;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupInterseptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (httpServletRequest.getRequestURI().equals("/AirbnbWeb/login")) {
            String email = httpServletRequest.getParameter("email");
            String password = httpServletRequest.getParameter("password");
            UserService userService = (UserService) Context.getContext().getBean("userService");
            if (userService.findUserByEmailAndPassword(email, password) == null) {
                httpServletRequest.setAttribute("h1", "Incorrect email or password. Please try again.");
                httpServletRequest.setAttribute("h2", "");
                httpServletRequest.getRequestDispatcher("/").forward(httpServletRequest, httpServletResponse);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
