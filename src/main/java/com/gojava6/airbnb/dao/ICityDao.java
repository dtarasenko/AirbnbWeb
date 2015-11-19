package com.gojava6.airbnb.dao;

import com.gojava6.airbnb.model.City;

import java.util.List;

public interface ICityDao {

    void createCity(City city);
    void updateCity(City city);
    void deleteCity(City city);
    List<City> getCityList();
    City getCity(int cityId);
}
