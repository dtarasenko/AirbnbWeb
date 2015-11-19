package com.gojava6.airbnb.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "subscriber")
public class Subscriber implements Serializable {

    @Id
    @OneToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "user=" + user +
                '}';
    }
}
