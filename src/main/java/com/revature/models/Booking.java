package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingid;


    @Column(name="date", nullable = false)
    private Date date;

    @Column(name="price", nullable = false)
    private double price;

    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnore
    private  User user;

    @ManyToOne(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinColumn(name="origin_city")
    private City origin;

    @ManyToOne(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinColumn(name="destination_city")
    private City destination;




    public Booking() {
    }

    public Booking(Date date, double price, User user, City origin, City destination) {
        this.date = date;
        this.price = price;
        this.user = user;
        this.origin = origin;
        this.destination = destination;
    }

    public Booking(int bookingid, Date date, double price, User user, City origin, City destination) {
        this.bookingid = bookingid;
        this.date = date;
        this.price = price;
        this.user = user;
        this.origin = origin;
        this.destination = destination;
    }

    public City getOrigin() {
        return origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    @Override
    public String toString() {
        return "Booking{" +
                "bookingid=" + bookingid +
                ", date=" + date +
                ", price=" + price +


                '}';
    }
}
