package com.revature.controllers;


import com.revature.models.Booking;
import com.revature.models.City;
import com.revature.models.User;
import com.revature.services.BookingService;
import com.revature.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class CityController {


    private CityService cs;

    @Autowired
    public CityController(CityService cs) {
        this.cs = cs;
    }

    @PostMapping("/city/")
    public ResponseEntity<Object> handleCreate(@RequestBody LinkedHashMap<String, String> u) {
        City c = new City(u.get("city"), Integer.parseInt(u.get("position")));



        return new ResponseEntity<>(cs.createCity(c), HttpStatus.CREATED);
    }


    @GetMapping("/city/get")
    public ResponseEntity<List> handleGet() {
        return new ResponseEntity<>(cs.getAllCities(), HttpStatus.ACCEPTED);

    }
}
