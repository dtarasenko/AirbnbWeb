package com.gojava6.airbnb.web;

import com.gojava6.airbnb.service.UserService;
import com.gojava6.airbnb.web.Listener.Context;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FilterSignup implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserService userService = (UserService) Context.getContext().getBean("userService");

        if (userService.findUserByEmailAndPassword(email, password) == null) {
            try {
                request.setAttribute("h1", "Incorrect email or password. Please try again.");
                request.setAttribute("h2", "");
                request.getRequestDispatcher("index.jsp").forward(servletRequest, servletResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
