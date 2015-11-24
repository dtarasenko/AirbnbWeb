package com.gojava6.airbnb.dao.jpa;

import com.gojava6.airbnb.dao.ICityDao;
import com.gojava6.airbnb.model.City;
import com.gojava6.airbnb.web.ListnerEMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CityDaoJpa implements ICityDao {

    public void createCity(City city) {
        EntityManager em = ListnerEMF.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(city);
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
        } finally {
            em.close();
        }
    }

    public void updateCity(City city) {
        EntityManager em = ListnerEMF.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                City updatedCity = em.find(City.class, city.getCityId());
                updatedCity.setCityName(city.getCityName());
                updatedCity.setUrl(city.getUrl());
                updatedCity.setImgName(city.getImgName());
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
        } finally {
            em.close();
        }
    }

    public void deleteCity(City city) {
        EntityManager em = ListnerEMF.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                City deletedCity = em.find(City.class, city.getCityId());
                em.remove(deletedCity);
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
        } finally {
            em.close();
        }
    }

    public List<City> getCityList() {
        EntityManager em = ListnerEMF.createEntityManager();
        List<City> cityList;
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                cityList = em.createQuery("SELECT c FROM City c").getResultList();
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
        } finally {
            em.close();
        }
        return cityList;
    }

    public City getCity(int cityId) {
        EntityManager em = ListnerEMF.createEntityManager();
        City city;
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                city = em.find(City.class, cityId);
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
        } finally {
            em.close();
        }
        return city;
    }
}
