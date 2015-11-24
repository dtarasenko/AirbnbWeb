package com.gojava6.airbnb.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FilterLogin implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);
        String logged = (String) session.getAttribute("logged-in");
        if (logged == null || logged.equals("false")) {
            try {
                request.setAttribute("h1", "Please log in to continue");
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
