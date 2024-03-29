package com.revature.repository;

import com.revature.models.Booking;
import com.revature.models.City;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepo extends JpaRepository<City, Integer>,  CrudRepository<City, Integer> {



    City findByCity(String city);

    City findByPosition(int position);
}
