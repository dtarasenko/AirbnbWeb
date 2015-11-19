package com.gojava6.airbnb.model;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int reservationId;

    @Column(name = "start")
    private long start;

    @Column(name = "end")
    private long end;

    @ManyToOne
    private User user;

    @ManyToOne
    private Apartment apartment;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date start = new Date(this.start);
        Date end = new Date(this.end);
        String stringStart = sdf.format(start);
        String stringEnd = sdf.format(end);

        return "Reservation{" +
                "apartment=" + apartment +
                ", reservationId=" + reservationId +
                ", start=" + start +
                ", end=" + end +
                ", user=" + user +
                '}';
    }
}
