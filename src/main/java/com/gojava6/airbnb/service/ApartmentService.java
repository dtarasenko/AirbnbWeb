package com.gojava6.airbnb.service;

import com.gojava6.airbnb.dao.IApartmentDao;
import com.gojava6.airbnb.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.sql.Date;

@Component
public class ApartmentService {

    @Autowired
    private IApartmentDao iApartmentDao;
    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;

    public ApartmentService() {
    }

    public Set<City> getCitiesWithApartments() {
        Set<City> citySet = new HashSet<>();
        for (Apartment apartment : getApartmentList()) {
            citySet.add(apartment.getCity());
        }
        return citySet;
    }

    public boolean isAvailable(Apartment apartment, Date start, Date end) {
        List<Reservation> reservationList = apartment.getReservationList();

        if (reservationList.isEmpty()) {
            return true;
        } else {
            for (Reservation rd : reservationList) {
                if (!(start.getTime() < rd.getStart().getTime() && end.getTime() < rd.getStart().getTime()) &&
                        !(start.getTime() > rd.getEnd().getTime() && end.getTime() > rd.getEnd().getTime())) {
                    return false;
                }
            }
        }
        return true;
    }

    public void createApartment(String apartmentDescription, ApartmentType apartmentType, int numberOfGuests, int price,
                                String userEmail, String cityName) {
        User user = userService.findUserByEmail(userEmail);
        City city = cityService.findCityByName(cityName);

        Apartment apartment = new Apartment();
        apartment.setApartmentDescription(apartmentDescription);
        apartment.setApartmentType(apartmentType.getApartmentType());
        apartment.setNumberOfGuests(numberOfGuests);
        apartment.setPrice(price);
        apartment.setUser(user);
        apartment.setCity(city);
        iApartmentDao.createApartment(apartment);
    }

    public void deleteApartment(Apartment apartment) {
        iApartmentDao.deleteApartment(apartment);
    }

    public Apartment getApartment(Integer apartmentId) {
        return iApartmentDao.getApartment(apartmentId);
    }

    public List<Apartment> getApartmentList() {
        return iApartmentDao.getApartmentList();
    }

}
