package com.gojava6.airbnb.dao.jpa;

import com.gojava6.airbnb.dao.IApartmentDao;
import com.gojava6.airbnb.model.Apartment;
import com.gojava6.airbnb.web.listener.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ApartmentDaoJPA implements IApartmentDao {

    public void createApartment(Apartment apartment) {
        EntityManager em = EMF.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(apartment);
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

    public void updateApartment(Apartment apartment) {
        EntityManager em = EMF.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                Apartment updatedApartment = em.find(Apartment.class, apartment.getApartmentId());
                updatedApartment.setApartmentDescription(apartment.getApartmentDescription());
                updatedApartment.setApartmentType(apartment.getApartmentType());
                updatedApartment.setNumberOfGuests(apartment.getNumberOfGuests());
                updatedApartment.setPrice(apartment.getPrice());
                updatedApartment.setCity(apartment.getCity());
                updatedApartment.setUser(apartment.getUser());
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

    public void deleteApartment(Apartment apartment) {
        EntityManager em = EMF.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                Apartment deletedApartment = em.find(Apartment.class, apartment.getApartmentId());
                em.remove(deletedApartment);
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

    public List<Apartment> getApartmentList() {
        EntityManager em = EMF.createEntityManager();
        List<Apartment> apartmentList;
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                apartmentList = em.createQuery("SELECT a FROM Apartment a").getResultList();
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
        } finally {
            em.close();
        }
        return apartmentList;
    }

    public Apartment getApartment(Integer apartmentId) {
        EntityManager em = EMF.createEntityManager();
        Apartment apartment;
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                apartment = em.find(Apartment.class, apartmentId);
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
        } finally {
            em.close();
        }
        return apartment;
    }
}
