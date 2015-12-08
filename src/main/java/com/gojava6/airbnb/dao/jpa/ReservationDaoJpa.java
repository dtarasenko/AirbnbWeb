package com.gojava6.airbnb.dao.jpa;

import com.gojava6.airbnb.dao.IReservationDao;
import com.gojava6.airbnb.model.Reservation;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class ReservationDaoJpa implements IReservationDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createReservation(Reservation reservation) {
        em.persist(reservation);
    }

    @Transactional
    public void updateReservation(Reservation reservation) {
        Reservation updatedReservation = em.find(Reservation.class, reservation.getReservationId());
        updatedReservation.setStart(reservation.getStart());
        updatedReservation.setEnd(reservation.getEnd());
        updatedReservation.setApartment(reservation.getApartment());
        updatedReservation.setUser(reservation.getUser());
    }

    @Transactional
    public void deleteReservation(Reservation reservation) {
        Reservation deletedReservation = em.find(Reservation.class, reservation.getReservationId());
        em.remove(deletedReservation);
    }

    @Transactional
    public List<Reservation> getReservationList() {
        return em.createQuery("SELECT r FROM Reservation r").getResultList();
    }

    @Transactional
    public List<Reservation> getApartmentReservationList(Integer apartmentId) {
        return null;
    }

    @Transactional
    public Reservation getReservation(Integer reservationId) {
        return em.find(Reservation.class, reservationId);
    }
}
