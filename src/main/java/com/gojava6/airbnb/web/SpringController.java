package com.gojava6.airbnb.web;


import com.gojava6.airbnb.model.*;
import com.gojava6.airbnb.service.ApartmentService;
import com.gojava6.airbnb.service.ReservationService;
import com.gojava6.airbnb.service.SearchService;
import com.gojava6.airbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Controller
public class SpringController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/")
    public String printIndex(ModelMap model) {
        model.addAttribute("cityList", apartmentService.getCitiesWithApartments());
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String printSearch(ModelMap model,
                              @RequestParam("city")String city) {
        searchService.setApartmentList();
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
        searchService.setApartmentList();
        searchService.filterByCity(city);
        searchService.filterByDates(startDate,endDate);
        searchService.filterByNumberOfGuests(numberOfGuests);
        searchService.filterByApartmentType(apartmentType);
        model.addAttribute("apartmentList", searchService.getApartmentList());
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "search";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String printContacts(ModelMap model,
                                @RequestParam("apartmentId")int apartmentId,
                                @RequestParam("startDate")String startDate,
                                @RequestParam("endDate")String endDate) {
        model.addAttribute("apartment", apartmentService.getApartment(apartmentId));
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
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
        userService.createUser(name, surname, email, UserType.CLIENT, password);
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("email")String email,
                        @RequestParam("password")String password,
                        HttpServletRequest request) {
        request.getSession().setAttribute("logged-in", "true");
        request.getSession().setAttribute("email", email);
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().setAttribute("logged-in", "false");
        return "redirect:/";
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
        apartmentService.createApartment(description, apartmentType, numberOfGuests, price, email, city);
        return "redirect:/";
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String printUsers(ModelMap model) {
        model.addAttribute("userList", userService.getUserList());
        return "adminusers";

    }

    @RequestMapping(value = "/admin/apartments", method = RequestMethod.GET)
    public String printApartments(ModelMap model) {
        model.addAttribute("apartmentList", apartmentService.getApartmentList());
        return "adminapartments";
    }

    @RequestMapping(value = "/admin/reservations", method = RequestMethod.GET)
    public String printReservations(ModelMap model) {
        model.addAttribute("reservationList", reservationService.getReservationList());
        return "adminreservations";
    }

    @RequestMapping(value = "/reservation", method = RequestMethod.GET)
    public String makeReservation(@RequestParam("apartmentId")int apartmentId,
                                  @RequestParam("startDate")String startDate,
                                  @RequestParam("endDate")String endDate,
                                  HttpServletRequest request) {
        java.util.Date startDateFormat = null;
        java.util.Date endDateFormat = null;
        try {
            startDateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            endDateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date startSqlFormat = new java.sql.Date(startDateFormat.getTime());
        java.sql.Date endtSqlFormat = new java.sql.Date(endDateFormat.getTime());

        String email = (String) request.getSession().getAttribute("email");
        User user = userService.findUserByEmail(email);
        Apartment apartment = apartmentService.getApartment(apartmentId);
        reservationService.createReservation(startSqlFormat, endtSqlFormat, user, apartment);
        return "redirect:myreservationlist";
    }

    @RequestMapping(value = "/myprofile", method = RequestMethod.GET)
    public String showMyProfile(ModelMap model, HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("email");
        model.addAttribute("user", userService.findUserByEmail(email));
        return "myprofile";
    }

    @RequestMapping(value = "/update_profile", method = RequestMethod.POST)
    public String updateUser(ModelMap model, HttpServletRequest request,
                             @RequestParam("name")String name,
                             @RequestParam("surname")String surname,
                             @RequestParam("email")String newEmail,
                             @RequestParam("password")String password) {
        String email = (String) request.getSession().getAttribute("email");
        User user = userService.findUserByEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(newEmail);
        user.setPassword(password);
        userService.updateUser(user);
        request.getSession().setAttribute("email", newEmail);
        model.addAttribute("user", userService.findUserByEmail(newEmail));
        return "redirect:myprofile";
    }

    @RequestMapping(value = "/myapartmentlist", method = RequestMethod.GET)
    public String showMyApartmentList(ModelMap model, HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("email");
        User user = userService.findUserByEmail(email);
        model.addAttribute("apartmentList", user.getApartmentList());
        model.addAttribute("h1", "My Apartments");
        model.addAttribute("h2", "");
        return "myapartmentlist";
    }

    @RequestMapping(value = "/myapartment", method = RequestMethod.GET)
    public String showMyApartment(@RequestParam("apartmentId")int apartmentId,
                                  ModelMap model) {
        Apartment apartment = apartmentService.getApartment(apartmentId);
        model.addAttribute("apartment", apartment);
        return "myapartment";
    }

    @RequestMapping(value = "/update_apartment", method = RequestMethod.POST)
    public String updateMyApartment(@RequestParam("apartment_id")int apartmentId,
                                    @RequestParam("description")String apartmentDescription,
                                    @RequestParam("type")String apartmentType,
                                    @RequestParam("number_of_guests")int numberOfGuests,
                                    @RequestParam("price")int price,
                                    ModelMap model) {
        Apartment apartment = apartmentService.getApartment(apartmentId);
        apartment.setApartmentDescription(apartmentDescription);
        apartment.setApartmentType(apartmentType);
        apartment.setNumberOfGuests(numberOfGuests);
        apartment.setPrice(price);
        apartmentService.updateApartment(apartment);
        apartment = apartmentService.getApartment(apartmentId);
        model.addAttribute("apartment", apartment);
        return "myapartment";
    }

    @RequestMapping(value = "/myreservationlist", method = RequestMethod.GET)
    public String showMyReservationList(ModelMap model, HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("email");
        User user = userService.findUserByEmail(email);
        model.addAttribute("reservationList", user.getReservationList());
        model.addAttribute("h1", "My Reservations");
        model.addAttribute("h2", "");
        return "myreservationlist";
    }

    @RequestMapping(value = "/delete_reservation", method = RequestMethod.POST)
    public String deleteMyReservation(@RequestParam("reservation_id")int reservationId) {
        Reservation reservation = reservationService.getReservation(reservationId);
        reservationService.deleteReservation(reservation);
        return "redirect:myreservationlist";
    }

}
