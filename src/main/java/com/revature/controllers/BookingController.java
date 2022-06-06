package com.revature.controllers;


import com.revature.models.Booking;
import com.revature.models.City;
import com.revature.models.Search;
import com.revature.models.User;
import com.revature.services.BookingService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class BookingController {

    private BookingService bs;

    @Autowired
    public BookingController(BookingService bs) {
        this.bs = bs;
    }

    @PostMapping("/booking/")
    public ResponseEntity<Object> handleCreate(@RequestBody LinkedHashMap<String, String> u) {


        Booking book = bs.createBooking(u.get("date"), Integer.parseInt(u.get("userId")), u.get("origin"), u.get("destination"));
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }

    }


//    @PutMapping("/booking/update")
//    public ResponseEntity<Object> handleUpdate(@RequestBody LinkedHashMap<String, String> u) {
//        double price = Double.parseDouble(u.get("price"));
//
//
//        Booking book = bs.updateBooking(Integer.parseInt(u.get("bookingid")), u.get("date"), price, Integer.parseInt(u.get("userId")), u.get("origin"), u.get("destination"));
//
//        return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
//    }


//    @DeleteMapping("/booking/delete/{id}")
//    public ResponseEntity<Object> handleDelete(@PathVariable("id") int id) {
//        bs.deleteBooking(id);
//
//        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
//    }

    //
////    @GetMapping("/user/get/{email}")
////    public ResponseEntity<Object> handleGet(@PathVariable ("email") String email){
////        return ResponseEntity<>(us.findCurrentUserByEmail(email), HttpStatus.ACCEPTED);
////    }
//
    @GetMapping("/booking/get/{id}")
    public ResponseEntity<List> handleGet(@PathVariable("id") int id) {
        return new ResponseEntity<>(bs.findCurrentBookingByUId(id), HttpStatus.ACCEPTED);

    }

//    @PostMapping("/booking/filter")
//    public ResponseEntity<Object> handleSearch(@RequestBody LinkedHashMap<String, String> u) {
//
//        List<Booking> book = bs.findCurrentBookingByDestination(u.get("date"), u.get("origin"), u.get("destination"));
//
//        if (book != null) {
//            return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
//        }
//
//    }

    @PostMapping("/booking/points")
    public ResponseEntity<Object> handleCreatePoints(@RequestBody LinkedHashMap<String, String> u) {


        Booking book = bs.createBookingPoints(u.get("date"), Integer.parseInt(u.get("userId")), u.get("origin"), u.get("destination"));
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }


//    @GetMapping("/booking/get/price")
//    public ResponseEntity<Object> handleGet() {
//        return new ResponseEntity<>(bs.findCurrentBookingByUId(id), HttpStatus.ACCEPTED);
//
//    }
//
//
    }


    @PostMapping("/booking/price")
    public ResponseEntity<Search> handlePrice(@RequestBody LinkedHashMap<String, String> u) {
        System.out.println(u.entrySet());
        return new ResponseEntity<>(bs.searchPrice(u.get("date"), u.get("origin"), u.get("destination")), HttpStatus.ACCEPTED);

    }


}