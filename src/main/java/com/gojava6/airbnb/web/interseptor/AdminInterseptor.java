package com.gojava6.airbnb.web.interseptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterseptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (httpServletRequest.getRequestURI().equals("/AirbnbWeb/admin")) {
            HttpSession session = httpServletRequest.getSession(false);
            String email = (String) session.getAttribute("email");
            String password = (String) session.getAttribute("password");
            if (email.equals("admin@admin.com") || password.equals("admin")) {
                httpServletRequest.setAttribute("h1", "Admin");
                httpServletRequest.setAttribute("h2", "");
                return true;
            }
        }
        httpServletRequest.setAttribute("h1", "Please log in to continue as admin");
        httpServletRequest.setAttribute("h2", "");
        httpServletRequest.getRequestDispatcher("/").forward(httpServletRequest, httpServletResponse);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
