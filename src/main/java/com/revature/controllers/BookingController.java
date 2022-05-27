package com.revature.controllers;


import com.revature.models.Booking;
import com.revature.models.City;
import com.revature.models.User;
import com.revature.services.BookingService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.LinkedHashMap;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {

    private BookingService bs;

    @Autowired
    public BookingController(BookingService bs) {
        this.bs = bs;
    }

    @PostMapping("/booking/")
    public ResponseEntity<Object> handleCreate(@RequestBody LinkedHashMap<String, String> u) {
        double price = Double.parseDouble(u.get("price"));


        Booking book = bs.createBooking(u.get("date"), price, Integer.parseInt(u.get("userId")), Integer.parseInt(u.get("origin")), Integer.parseInt(u.get("destination")));

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }


    @PutMapping("/booking/update")
    public ResponseEntity<Object> handleUpdate(@RequestBody LinkedHashMap<String, String> u) {
        double price = Double.parseDouble(u.get("price"));



        Booking book = bs.updateBooking(Integer.parseInt(u.get("bookingid")),u.get("date"), price, Integer.parseInt(u.get("userId")), Integer.parseInt(u.get("origin")), Integer.parseInt(u.get("destination")));

        return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
    }



    @DeleteMapping("/booking/delete/{id}")
    public ResponseEntity<Object> handleDelete(@PathVariable ("id") int id) {
        bs.deleteBooking(id);

        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }
//
////    @GetMapping("/user/get/{email}")
////    public ResponseEntity<Object> handleGet(@PathVariable ("email") String email){
////        return ResponseEntity<>(us.findCurrentUserByEmail(email), HttpStatus.ACCEPTED);
////    }
//
//    @GetMapping("/booking/get/{id}")
//    public User handleGet(@PathVariable ("id") int id){
//        return us.findCurrentUserById(id);
//    }
}
