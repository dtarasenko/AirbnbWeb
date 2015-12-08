package com.gojava6.airbnb.dao.jpa;

import com.gojava6.airbnb.dao.ICityDao;
import com.gojava6.airbnb.model.City;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CityDaoJpa implements ICityDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createCity(City city) {
        em.persist(city);
    }

    @Transactional
    public void updateCity(City city) {
        City updatedCity = em.find(City.class, city.getCityId());
        updatedCity.setCityName(city.getCityName());
        updatedCity.setUrl(city.getUrl());
        updatedCity.setImgName(city.getImgName());
    }

    @Transactional
    public void deleteCity(City city) {
        City deletedCity = em.find(City.class, city.getCityId());
        em.remove(deletedCity);
    }

    @Transactional
    public List<City> getCityList() {
        return em.createQuery("SELECT c FROM City c").getResultList();
    }

    public City getCity(int cityId) {
        return em.find(City.class, cityId);
    }
}
