package com.gojava6.airbnb.service;

import com.gojava6.airbnb.dao.IReservationDao;
import com.gojava6.airbnb.model.Apartment;
import com.gojava6.airbnb.model.Reservation;
import com.gojava6.airbnb.model.User;

import java.sql.Date;
import java.util.List;

public class ReservationService {

    IReservationDao iReservationDao;

    public ReservationService(IReservationDao iReservationDao) {
        this.iReservationDao = iReservationDao;
    }

    public void createReservation(Date start, Date end, User user, Apartment apartment) {
        Reservation reservation = new Reservation();
        reservation.setStart(start);
        reservation.setEnd(end);
        reservation.setUser(user);
        reservation.setApartment(apartment);

        iReservationDao.createReservation(reservation);
    }

    public void updateReservationDates(int reservationId, Date start, Date end) {
        Reservation reservation = iReservationDao.getReservation(reservationId);
        reservation.setStart(start);
        reservation.setEnd(end);
        iReservationDao.updateReservation(reservation);
    }

    public void deleteReservation(Reservation reservation) {
        iReservationDao.deleteReservation(reservation);
    }

    public List<Reservation> getReservationList() {
        return iReservationDao.getReservationList();
    }

    public List<Reservation> getApartmentReservationList(int apartmentId) {
        return iReservationDao.getApartmentReservationList(apartmentId);
    }

    public Reservation getReservation(int reservationId) {
        return iReservationDao.getReservation(reservationId);
    }

}
