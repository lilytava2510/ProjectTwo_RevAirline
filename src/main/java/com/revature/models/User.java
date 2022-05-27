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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="points")
    private int points;

    @Column(name="role", nullable = false)
    private int role;


    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Booking> bookingList;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="creditcard_number")
    private int ccn;

    @Column(name="vaccination_status")
    private boolean sick;

    @Column(name="passport_number")
    private int ppn;


    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.bookingList = new ArrayList<>();
    }

    public User(int userid, int ccn, String email, String firstname, String lastname, String password, int points, int ppn, int role, boolean sick) {
        this.userId = userid;
        this.email = email;
        this.password = password;
        this.points = points;
        this.role = role;
        this.firstName = firstname;
        this.lastName = lastname;
        this.ccn = ccn;
        this.sick = sick;
        this.ppn = ppn;
        this.bookingList = new ArrayList<>();
    }

    public User(String email, String password, int points, int role, String firstName, String lastName, int ccn, boolean sick, int ppn) {
        this.email = email;
        this.password = password;
        this.points = points;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ccn = ccn;
        this.sick = sick;
        this.ppn = ppn;
        this.bookingList = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userid) {
        this.userId = userid;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getCcn() {
        return ccn;
    }

    public void setCcn(int ccn) {
        this.ccn = ccn;
    }

    public boolean isSick() {
        return sick;
    }

    public void setSick(boolean sick) {
        this.sick = sick;
    }

    public int getPpn() {
        return ppn;
    }

    public void setPpn(int ppn) {
        this.ppn = ppn;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", points=" + points +
                ", role=" + role +
                ", bookingList=" + bookingList +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ccn=" + ccn +
                ", sick=" + sick +
                ", ppn=" + ppn +
                '}';
    }
}
