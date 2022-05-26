package com.revature.controllers;


import com.revature.models.City;
import com.revature.models.User;
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
//
//    private BookingService bs;
//
//    @Autowired
//    public BookingController(BookingService bs) {
//        this.bs = bs;
//    }
//
//    @PostMapping("/booking/")
//    public ResponseEntity<Object> handleCreate(@RequestBody LinkedHashMap<String, String> u) {
//        double price = Double.parseDouble(u.get("price"));
//        User we = new User();
//        we.setUserId(Integer.parseInt(u.get("userId")));
//        City origin = new City();
//        City destination = new City();
//        origin.setCity(u.get("origin"));
//        destination.setCity(u.get("destination"));
//        Date date = u.get("date");
//        User we = us.createUser(u.get("email"), u.get("password"), points, role, u.get("firstName"), u.get("lastName"), ccn, sick, ppn);
//
//        return new ResponseEntity<>(we, HttpStatus.CREATED);
//    }
//
////    @PostMapping("/user/login")
////    public ResponseEntity<Object> handleLoginUser(@RequestBody LinkedHashMap<String, String> u) {
////
////        String email = u.get("email");
////        String password = u.get("password");
////
////        try {
////            User we = us.getUserByEmailAndPassword(email, password);
////            System.out.println(we.toString());
////            return new ResponseEntity<>(we, HttpStatus.ACCEPTED);
////        } catch (Exception e) {
////            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
////        }
////    }
//    @PutMapping("/booking/update")
//    public ResponseEntity<Object> handleUpdate(@RequestBody LinkedHashMap<String, String> u) {
//        boolean sick = u.get("sick").equals("true");
//        int points = Integer.parseInt(u.get("points"));
//        int role = Integer.parseInt(u.get("role"));
//        int ccn = Integer.parseInt(u.get("ccn"));
//        int ppn = Integer.parseInt(u.get("ppn"));
//        int userId = Integer.parseInt(u.get("userId"));
//
//
//        User we = bs.updateUser(userId, ccn, u.get("email"), u.get("firstName"), u.get("lastName"), u.get("password"), points, ppn, role, sick);
//
//        return new ResponseEntity<>(we, HttpStatus.ACCEPTED);
//    }
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

