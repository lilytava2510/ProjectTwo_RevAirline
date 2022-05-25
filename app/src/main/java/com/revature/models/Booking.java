package com.revature.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="bookings")
public class Booking {

    @Id
    @Column(name="bookingid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingid;


    @Column(name="date", nullable = false)
    private Date date;

    @Column(name="price", nullable = false)
    private double price;

    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="userid")
    private  int userid;

    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="city")
    private String origin;


    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="city")
    private String destination;

    public Booking(Date date, double price, int userid, String origin, String destination) {
        this.date = date;
        this.price = price;
        this.userid = userid;
        this.origin = origin;
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingid=" + bookingid +
                ", date=" + date +
                ", price=" + price +
                ", userid=" + userid +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
