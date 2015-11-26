package com.gojava6.airbnb.web;


import com.gojava6.airbnb.service.ApartmentService;
import com.gojava6.airbnb.service.SearchService;
import com.gojava6.airbnb.service.UserService;
import com.gojava6.airbnb.web.Listener.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpringController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printIndex(ModelMap model) {
        ApartmentService apartmentService = (ApartmentService) Context.getContext().getBean("apartmentService");
        model.addAttribute("cityList", apartmentService.getCitiesWithApartments());
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String printSearch(ModelMap model, @RequestParam("city")String city) {
        SearchService searchService = new SearchService();
        searchService.filterByCity(city);
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


}
