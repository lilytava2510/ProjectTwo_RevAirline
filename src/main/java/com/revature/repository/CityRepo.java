package com.revature.repository;

import com.revature.models.Booking;
import com.revature.models.City;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepo extends JpaRepository<City, Integer>,  CrudRepository<City, Integer> {

}