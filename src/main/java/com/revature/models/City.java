package com.revature.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cityId;

    @Column(name = "name")
    private String city;


    @OneToMany(mappedBy = "origin", cascade = CascadeType.ALL)
    private List<Booking> departures;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<Booking> arrivals;


    @Column(name = "position")
    private int position;


    public City() {
    }

    public City(String city, int position) {
        this.city = city;
        this.position = position;
        this.arrivals = new ArrayList<>();
        this.departures = new ArrayList<>();
    }

    public City(int cityId, String city, List<Booking> departures, List<Booking> arrivals, int position) {
        this.cityId = cityId;
        this.city = city;
        this.departures = departures;
        this.arrivals = arrivals;
        this.position = position;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityid) {
        this.cityId = cityid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Booking> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Booking> departures) {
        this.departures = departures;
    }

    public List<Booking> getArrivals() {
        return arrivals;
    }

    public void setArrivals(List<Booking> arrivals) {
        this.arrivals = arrivals;
    }
}
