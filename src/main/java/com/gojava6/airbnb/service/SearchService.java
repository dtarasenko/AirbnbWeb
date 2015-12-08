package com.gojava6.airbnb.service;

import com.gojava6.airbnb.model.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchService {

    @Autowired
    private ApartmentService apartmentService;
    private List<Apartment> apartmentList;

    public SearchService() {
    }

    public void setApartmentList() {
        this.apartmentList = apartmentService.getApartmentList();
    }

    public List<Apartment> getApartmentList() {
        return apartmentList;
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

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date startDateFormat = null;
            java.util.Date endDateFormat = null;
            try {
                startDateFormat = format.parse(startDate);
                endDateFormat = format.parse(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            java.sql.Date startSqlFormat = new java.sql.Date(startDateFormat.getTime());
            java.sql.Date endSqlFormat = new java.sql.Date(endDateFormat.getTime());

            for (Apartment apartment : apartmentList) {
                if (apartmentService.isAvailable(apartment, startSqlFormat, endSqlFormat)) {
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
