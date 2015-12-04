package com.gojava6.airbnb.service;

import com.gojava6.airbnb.model.Apartment;
import com.gojava6.airbnb.model.ApartmentType;
import com.gojava6.airbnb.web.listener.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchService {

    private List<Apartment> apartmentList;

    public List<Apartment> getApartmentList() {
        return apartmentList;
    }

    public SearchService() {
        ApartmentService apartmentService = (ApartmentService) Context.getContext().getBean("apartmentService");
        this.apartmentList = apartmentService.getApartmentList();
    }

    public void filterByCity(String city) {
        List<Apartment> list = new ArrayList<>();
        for (Apartment apartment : apartmentList) {
            String cityName = apartment.getCity().getCityName();
            if (cityName.equals(city)) {
                list.add(apartment);
            }
        }
        apartmentList = list;
    }

    public void filterByDates(String startDate, String endDate){
        if (!startDate.equals("") && !endDate.equals("")) {
            List<Apartment> list = new ArrayList<>();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date startDateFormat = null;
            Date endDateFormat = null;
            try {
                startDateFormat = format.parse(startDate);
                endDateFormat = format.parse(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long startLongFormat = startDateFormat.getTime();
            long endLongFormat = endDateFormat.getTime();

            ApartmentService apartmentService = (ApartmentService) Context.getContext().getBean("apartmentService");

            for (Apartment apartment : apartmentList) {
                if (apartmentService.isAvailable(apartment, startLongFormat, endLongFormat)) {
                    list.add(apartment);
                }
            }
            apartmentList = list;
        }
    }

    public void filterByNumberOfGuests(Integer numberOfGuests) {
        if (numberOfGuests > 0) {
            List<Apartment> list = new ArrayList<>();
            for (Apartment apartment : apartmentList) {
                Integer guests = apartment.getNumberOfGuests();
                if (guests.equals(numberOfGuests)) {
                    list.add(apartment);
                }
            }
            apartmentList = list;
        }
    }

    public void filterByApartmentType(String apartmentType) {
        if (!apartmentType.equals("blank")) {
            List<Apartment> list = new ArrayList<>();
            for (Apartment apartment : apartmentList) {
                if (apartmentType.equals(apartment.getApartmentType())) {
                    list.add(apartment);
                }
            }
            apartmentList = list;
        }
    }

}
