package com.gojava6.airbnb.service;

import com.gojava6.airbnb.dao.IApartmentDao;
import com.gojava6.airbnb.model.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApartmentService {

    IApartmentDao iApartmentDao;

    public ApartmentService(IApartmentDao iApartmentDao) {
        this.iApartmentDao = iApartmentDao;
    }



    public Set<City> getCitiesWithApartments() {
        Set<City> citySet = new HashSet<City>();
        for (Apartment apartment : getApartmentList()) {
            citySet.add(apartment.getCity());
        }
        return citySet;
    }

    public boolean isAvailable(Apartment apartment, long start, long end) {
        List<Reservation> reservationList = apartment.getReservationList();

        if (reservationList.isEmpty()) {
            return true;
        } else {
            for (Reservation rd : reservationList) {
                if (!(start < rd.getStart() && end < rd.getStart()) &&
                        !(start > rd.getEnd() && end > rd.getEnd())) {
                    return false;
                }
            }
        }
        return true;
    }



    public void createApartment(String apartmentDescription, ApartmentType apartmentType, int numberOfGuests, int price,
                                User user, City city) {
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
