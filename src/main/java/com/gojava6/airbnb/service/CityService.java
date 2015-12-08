package com.gojava6.airbnb.service;

import com.gojava6.airbnb.dao.ICityDao;
import com.gojava6.airbnb.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityService {

    @Autowired
    ICityDao iCityDao;

    public CityService() {
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
