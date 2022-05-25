package com.revature.models;

import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.Tables;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {


    @Id
    @Column(name="userid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userid;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="points")
    private int points;

    @Column(name="role", nullable = false)
    private int role;


    @OneToMany(mappedBy="userid", cascade = CascadeType.ALL)
    List<Booking> bookingList;


    public User(String email, String password, int role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.bookingList = new ArrayList<>();
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.bookingList = new ArrayList<>();
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", points=" + points +
                ", role=" + role +
                ", bookingList=" + bookingList +
                '}';
    }
}
