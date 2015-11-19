package com.gojava6.airbnb.service;

import com.gojava6.airbnb.dao.ICityDao;

public class CityService {

    ICityDao iCityDao;

    public CityService(ICityDao iCityDao) {
        this.iCityDao = iCityDao;
    }

}
