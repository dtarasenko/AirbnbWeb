package com.gojava6.airbnb.service;

import com.gojava6.airbnb.dao.ICityDao;
import com.gojava6.airbnb.model.City;

import java.util.List;

public class CityService {

    ICityDao iCityDao;

    public CityService(ICityDao iCityDao) {
        this.iCityDao = iCityDao;
    }

    public City findCityByName(String cityName) {
        for (City city : getCityList()) {
            if (city.getCityName().equals(cityName)) {
                return city;
            }
        }
        return null;
    }

    public List<City> getCityList() {
        return iCityDao.getCityList();
    }
}
