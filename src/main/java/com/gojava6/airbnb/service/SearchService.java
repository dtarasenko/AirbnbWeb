package com.gojava6.airbnb.service;

import com.gojava6.airbnb.model.Apartment;
import com.gojava6.airbnb.web.Listener.Context;

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

    public void showSearchResults() {
        System.out.println("\nSearchService results:");
        for (Apartment apartment : apartmentList) {
            System.out.println(apartment.toString());
        }
    }

    public void filterByCity(String city) {
        List<Apartment> list = new ArrayList<Apartment>();
        for (Apartment apartment : apartmentList) {
            String cityName = apartment.getCity().getCityName();
            if (cityName.equals(city)) {
                list.add(apartment);
            }
        }
        apartmentList = list;
    }

    public void filterByApartmentType(String place, String room, String apartment) {

        List<Apartment> list = new ArrayList<Apartment>();

        for (Apartment ap : apartmentList) {

            String apartmentType = ap.getApartmentType();

            if (apartmentType.equals(place)) {
                list.add(ap);
            } else if (apartmentType.equals(room)) {
                list.add(ap);
            } else if (apartmentType.equals(apartment)) {
                list.add(ap);
            }
        }
        apartmentList = list;
    }

    public void filterByDates(Date startDate, Date endDate){
        ApartmentService apartmentService = (ApartmentService) Context.getContext().getBean("apartmentService");

        long start = startDate.getTime();
        long end = endDate.getTime();

        List<Apartment> list = new ArrayList<Apartment>();

        for (Apartment apartment : apartmentList) {
            if (apartmentService.isAvailable(apartment, start, end)) {
                list.add(apartment);
            }
        }
        apartmentList = list;
    }

}
