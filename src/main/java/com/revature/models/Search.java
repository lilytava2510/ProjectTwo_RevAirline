package com.revature.models;

import java.sql.Date;

public class Search {
    private City origin ;
    private City destination;
    double price = 0.0;

    Date date;

    public Search() {
    }

    public Search(City origin, City destination, double price, Date time) {
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.date =time;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date time) {
        this.date = time;
    }
}
