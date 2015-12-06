package com.gojava6.airbnb.web;


import com.gojava6.airbnb.model.ApartmentType;
import com.gojava6.airbnb.model.UserType;
import com.gojava6.airbnb.service.ApartmentService;
import com.gojava6.airbnb.service.ReservationService;
import com.gojava6.airbnb.service.SearchService;
import com.gojava6.airbnb.service.UserService;
import com.gojava6.airbnb.web.listene.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SpringController {

    @RequestMapping(value = "/")
    public String printIndex(ModelMap model) {
        ApartmentService apartmentService = (ApartmentService) Context.getContext().getBean("apartmentService");
        model.addAttribute("cityList", apartmentService.getCitiesWithApartments());
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String printSearch(ModelMap model,
                              @RequestParam("city")String city) {
        SearchService searchService = new SearchService();
        searchService.filterByCity(city);
        model.addAttribute("apartmentList", searchService.getApartmentList());
        return "search";
    }

    @RequestMapping(value = "/advancedsearch", method = RequestMethod.GET)
    public String printAdvancedSearch(ModelMap model,
                              @RequestParam("city")String city,
                              @RequestParam("date_start")String startDate,
                              @RequestParam("date_end")String endDate,
                              @RequestParam("number_of_guests")Integer numberOfGuests,
                              @RequestParam("apartment_type")String apartmentType) {
        SearchService searchService = new SearchService();
        searchService.filterByCity(city);
        searchService.filterByDates(startDate,endDate);
        searchService.filterByNumberOfGuests(numberOfGuests);
        searchService.filterByApartmentType(apartmentType);
        model.addAttribute("apartmentList", searchService.getApartmentList());
        return "search";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String printContacts(ModelMap model, @RequestParam("hostId")int hostId) {
        UserService userService = (UserService) Context.getContext().getBean("userService");
        model.addAttribute("host", userService.getUser(hostId));
        return "contacts";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String printAdmin(ModelMap model) {
        model.addAttribute("h1", "Admin page");
        model.addAttribute("h2", "");
        return "admin";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String addUser(@RequestParam("name")String name,
                          @RequestParam("surname")String surname,
                          @RequestParam("email")String email,
                          @RequestParam("password")String password) {
        UserService userService = (UserService) Context.getContext().getBean("userService");
        userService.createUser(name, surname, email, UserType.CLIENT, password);
        return "redirect:";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("email")String email,
                        @RequestParam("password")String password,
                        HttpServletRequest request) {
        request.getSession().setAttribute("logged-in", "true");
        request.getSession().setAttribute("email", email);
        return "redirect:";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().setAttribute("logged-in", "false");
        return "redirect:";
    }


    @RequestMapping(value = "/addapartment", method = RequestMethod.POST)
    public String addApartment(@RequestParam("description")String description,
                               @RequestParam("apartment_type")int type,
                               @RequestParam("number_of_guests")int numberOfGuests,
                               @RequestParam("price")int price,
                               @RequestParam("city")String city,
                               HttpServletRequest request) {
        ApartmentType apartmentType = ApartmentType.values()[type];
        String email = (String)request.getSession().getAttribute("email");
        ApartmentService apartmentService = (ApartmentService) Context.getContext().getBean("apartmentService");
        apartmentService.createApartment(description, apartmentType, numberOfGuests, price, email, city);
        return "redirect:";
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String printUsers(ModelMap model) {
        UserService userService = (UserService) Context.getContext().getBean("userService");
        model.addAttribute("userList", userService.getUserList());
        return "adminusers";

    }

    @RequestMapping(value = "/admin/apartments", method = RequestMethod.GET)
    public String printApartments(ModelMap model) {
        ApartmentService apartmentService = (ApartmentService) Context.getContext().getBean("apartmentService");
        model.addAttribute("apartmentList", apartmentService.getApartmentList());
        return "adminapartments";
    }

    @RequestMapping(value = "/admin/reservations", method = RequestMethod.GET)
    public String printReservations(ModelMap model) {
        ReservationService reservationService = (ReservationService) Context.getContext().getBean("reservationService");
        model.addAttribute("reservationList", reservationService.getReservationList());
        return "adminreservations";
    }
}
