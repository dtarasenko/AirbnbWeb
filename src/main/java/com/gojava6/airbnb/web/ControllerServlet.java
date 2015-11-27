package com.gojava6.airbnb.web;

import com.gojava6.airbnb.model.ApartmentType;
import com.gojava6.airbnb.model.UserType;
import com.gojava6.airbnb.service.ApartmentService;
import com.gojava6.airbnb.service.SearchService;
import com.gojava6.airbnb.service.UserService;
import com.gojava6.airbnb.web.Listener.Context;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userPath = request.getServletPath();
        String urlView = "/WEB-INF/view" + userPath + ".jsp";
        String urlLogin = "/WEB-INF/login" + userPath + ".jsp";

        HttpSession session = request.getSession(true);

        if
//                (userPath.equals("")) {
//
//            ApartmentService apartmentService = (ApartmentService) Context.getContext().getBean("apartmentService");
//            request.setAttribute("cityList", apartmentService.getCitiesWithApartments());
//            request.getRequestDispatcher("index.jsp").forward(request, response);
//
//        } else if
//                (userPath.equals("/search")) {
//
//            String city = request.getParameter("city");
//            SearchService searchService = new SearchService();
//            searchService.filterByCity(city);
//            request.setAttribute("apartmentList", searchService.getApartmentList());
//            request.getRequestDispatcher(urlView).forward(request, response);

//        } else if (userPath.equals("/admin")) {
//
//            request.setAttribute("h1", "Admin page");
//            request.setAttribute("h2", "");
//            request.getRequestDispatcher(urlView).forward(request, response);

//        } else if
                (userPath.equals("/adminusers")) {

            request.setAttribute("h1", "Admin page");
            request.setAttribute("h2", "");
            request.getRequestDispatcher(urlView).forward(request, response);

        } else if (userPath.equals("/adminapartments")) {

            request.setAttribute("h1", "Admin page");
            request.setAttribute("h2", "");
            request.getRequestDispatcher(urlView).forward(request, response);

        } else if (userPath.equals("/adminreservations")) {

            request.setAttribute("h1", "Admin page");
            request.setAttribute("h2", "");
            request.getRequestDispatcher(urlView).forward(request, response);

        } else if (userPath.equals("/contacts")) {

//            String hostId = request.getParameter("hostId");
//            UserService userService = (UserService) Context.getContext().getBean("userService");
//            request.setAttribute("host", userService.getUser(Integer.parseInt(hostId)));
//            request.getRequestDispatcher(urlView).forward(request, response);

        } else if (userPath.equals("/logout")) {

//            session.setAttribute("logged-in", "false");
//            request.getRequestDispatcher("/").forward(request, response);

        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userPath = request.getServletPath();
        HttpSession session = request.getSession(true);

        if (userPath.equals("/signup")) {

//            String name = request.getParameter("name");
//            String surname = request.getParameter("surname");
//            String email = request.getParameter("email");
//            String password = request.getParameter("password");
//            UserService userService = (UserService) Context.getContext().getBean("userService");
//            userService.createUser(name, surname, email, UserType.CLIENT, password);
//            response.sendRedirect("http://localhost:8080/AirbnbWeb/");

        } else if (userPath.equals("/login")) {

//            String email = request.getParameter("email");
//            String password = request.getParameter("password");
//            session.setAttribute("logged-in", "true");
//            session.setAttribute("email", email);
//            response.sendRedirect("http://localhost:8080/AirbnbWeb/");

        } else if (userPath.equals("/addapartment")) {

            String apartmentDescription = request.getParameter("description");
            ApartmentType apartmentType = ApartmentType.values()[Integer.parseInt(request.getParameter("apartment_type"))];
            int numberOfGuests =Integer.parseInt(request.getParameter("number_of_guests"));
            int price = Integer.parseInt(request.getParameter("price"));
            String email = (String) session.getAttribute("email");
            String city = request.getParameter("city");

            ApartmentService apartmentService = (ApartmentService) Context.getContext().getBean("apartmentService");
            apartmentService.createApartment(apartmentDescription, apartmentType,
                    numberOfGuests, price, email, city);
            response.sendRedirect("http://localhost:8080/AirbnbWeb/");
        }
    }
}