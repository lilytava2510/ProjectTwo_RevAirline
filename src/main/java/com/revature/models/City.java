package com.revature.models;


import javax.persistence.*;

@Entity
@Table(name="cities")
public class City {

    @Id
    @Column(name="city" )
    public static String city;


    @Column(name="position")
    public static int position;


    public City(){
    }

    public City(String city, int position) {
        this.city = city;
        this.position = position;
    }


}
