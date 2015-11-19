package com.gojava6.airbnb.dao.jpa;

import com.gojava6.airbnb.dao.ICityDao;
import com.gojava6.airbnb.model.City;
import com.gojava6.airbnb.web.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CityDaoJpa implements ICityDao {

    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();

    public void createCity(City city) {
        EntityManager em = emf.createEntityManager();
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
        EntityManager em = emf.createEntityManager();
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
        EntityManager em = emf.createEntityManager();
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
        EntityManager em = emf.createEntityManager();
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
        EntityManager em = emf.createEntityManager();
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
