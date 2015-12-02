package com.gojava6.airbnb.web.interseptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterseptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (httpServletRequest.getRequestURI().equals("/AirbnbWeb/contacts") ||
            httpServletRequest.getRequestURI().equals("/AirbnbWeb/addapartment")) {
            HttpSession session = httpServletRequest.getSession(false);
            String logged = (String) session.getAttribute("logged-in");
            if (logged == null || logged.equals("false")) {
                httpServletRequest.setAttribute("h1", "Please log in to continue");
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
