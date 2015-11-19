package com.gojava6.airbnb.model;

import org.apache.log4j.Logger;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "apartment")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int apartmentId;

    @Column(name = "apartment_description", nullable = false, length = 100)
    private String apartmentDescription;

    @Column(name = "apartment_type", nullable = false)
    private String apartmentType;

    @Column(name = "number_of_guests", nullable = false)
    private int numberOfGuests;

    @Column(name = "price", nullable = false)
    private int price;

    @ManyToOne
    private User user;

    @ManyToOne
    private City city;

    @OneToMany(mappedBy="apartment", targetEntity = Reservation.class, fetch = FetchType.EAGER)
    private List<Reservation> reservationList;

    public Apartment() {
        super();
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getApartmentDescription() {
        return apartmentDescription;
    }

    public void setApartmentDescription(String apartmentDescription) {
        this.apartmentDescription = apartmentDescription;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "apartmentDescription='" + apartmentDescription + '\'' +
                ", apartmentId=" + apartmentId +
                ", apartmentType='" + apartmentType + '\'' +
                ", numberOfGuests=" + numberOfGuests +
                ", price=" + price +
                ", user=" + user +
                ", city=" + city +
                '}';
    }
}
