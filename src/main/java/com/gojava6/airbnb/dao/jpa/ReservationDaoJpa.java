package com.gojava6.airbnb.dao.jpa;

import com.gojava6.airbnb.dao.IReservationDao;
import com.gojava6.airbnb.model.Reservation;
import com.gojava6.airbnb.web.Listener.EMF;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ReservationDaoJpa implements IReservationDao {

    public void createReservation(Reservation reservation) {
        EntityManager em = EMF.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(reservation);
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

    public void updateReservation(Reservation reservation) {
        EntityManager em = EMF.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                Reservation updatedReservation = em.find(Reservation.class, reservation.getReservationId());
                updatedReservation.setStart(reservation.getStart());
                updatedReservation.setEnd(reservation.getEnd());
                updatedReservation.setApartment(reservation.getApartment());
                updatedReservation.setUser(reservation.getUser());
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

    public void deleteReservation(Reservation reservation) {
        EntityManager em = EMF.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                Reservation deletedReservation = em.find(Reservation.class, reservation.getReservationId());
                em.remove(deletedReservation);
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

    public List<Reservation> getReservationList() {
        EntityManager em = EMF.createEntityManager();
        List<Reservation> reservationList;
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                reservationList = em.createQuery("SELECT r FROM Reservation r").getResultList();
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
        } finally {
            em.close();
        }

        return reservationList;
    }

    public List<Reservation> getApartmentReservationList(Integer apartmentId) {
        return null;
    }

    public Reservation getReservation(Integer reservationId) {
        EntityManager em = EMF.createEntityManager();
        Reservation reservation;
        try {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                reservation = em.find(Reservation.class, reservationId);
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
        } finally {
            em.close();
        }
        return reservation;
    }
}
