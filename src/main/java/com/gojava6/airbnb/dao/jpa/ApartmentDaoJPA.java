package com.gojava6.airbnb.dao.jpa;

import com.gojava6.airbnb.dao.IApartmentDao;
import com.gojava6.airbnb.model.Apartment;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ApartmentDaoJPA implements IApartmentDao {

    @PersistenceContext(unitName = "myEmf")
    private EntityManager em;

    @Transactional
    public void createApartment(Apartment apartment) {
        em.persist(apartment);
    }

    @Transactional
    public void updateApartment(Apartment apartment) {
        Apartment updatedApartment = em.find(Apartment.class, apartment.getApartmentId());
        updatedApartment.setApartmentDescription(apartment.getApartmentDescription());
        updatedApartment.setApartmentType(apartment.getApartmentType());
        updatedApartment.setNumberOfGuests(apartment.getNumberOfGuests());
        updatedApartment.setPrice(apartment.getPrice());
        updatedApartment.setCity(apartment.getCity());
        updatedApartment.setUser(apartment.getUser());
    }

    @Transactional
    public void deleteApartment(Apartment apartment) {
        Apartment deletedApartment = em.find(Apartment.class, apartment.getApartmentId());
        em.remove(deletedApartment);
    }

    @Transactional
    public List<Apartment> getApartmentList() {
        return em.createQuery("SELECT a FROM Apartment a").getResultList();
    }

    @Transactional
    public Apartment getApartment(Integer apartmentId) {
        return em.find(Apartment.class, apartmentId);
    }
}
