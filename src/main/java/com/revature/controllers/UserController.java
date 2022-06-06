package com.revature.controllers;


import com.revature.models.User;
import com.revature.services.EmailSenderService;
import com.revature.services.UserService;
import com.revature.util.LoggingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://revairline-p2.s3.amazonaws.com")
public class UserController {

    private UserService us;

    @Autowired
    private EmailSenderService es;

    @Autowired
    public UserController(UserService us) {
        this.us = us;
    }

    @PostMapping("/user/")
    public ResponseEntity<Object> handleRegister(@RequestBody LinkedHashMap<String, String> u) {
        boolean sick = u.get("sick").equals("true");
        int points = Integer.parseInt(u.get("points"));
        int role = Integer.parseInt(u.get("role"));
        int ccn = Integer.parseInt(u.get("ccn"));
        int ppn = Integer.parseInt(u.get("ppn"));

        try{
            User we = us.createUser(u.get("email"), u.get("password"), points, role, u.get("firstName"), u.get("lastName"), ccn, sick, ppn);
            if(we != null){
                es.sendEmail(we.getEmail(),
                        "Welcome to RevAir!",
                        "Your account has been created with a userID of: "+ we.getUserId() + "and Password: "+ we.getPassword());
                return new ResponseEntity<>(we, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }


        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

//    @PostMapping("/user/")
//    public ResponseEntity<Object> handleRegister(@RequestBody LinkedHashMap<String, String> u) {
//        boolean sick = u.get("sick").equals("true");
//        int points = Integer.parseInt(u.get("points"));
//        int role = Integer.parseInt(u.get("role"));
//        int ccn = Integer.parseInt(u.get("ccn"));
//        int ppn = Integer.parseInt(u.get("ppn"));
//
//        User we = us.createUser(u.get("email"), u.get("password"), points, role, u.get("firstName"), u.get("lastName"), ccn, sick, ppn);
//
//        return new ResponseEntity<>(we, HttpStatus.CREATED);
//    }

    @PostMapping("/user/login")
    public ResponseEntity<Object> handleLoginUser(@RequestBody LinkedHashMap<String, String> u) {

        String email = u.get("email");
        String password = u.get("password");

        try {
            User we = us.getUserByEmailAndPassword(email, password);
            if(we == null) {

                LoggingUtil.logger.warn("improper attempt at login on email: " + email);
                return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
            }else{System.out.println(we.toString());
                return new ResponseEntity<>(we, HttpStatus.ACCEPTED);}
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @PutMapping("/user/update")
    public ResponseEntity<Object> handleUpdate(@RequestBody LinkedHashMap<String, String> u) {
        boolean sick = u.get("sick").equals("true");
        int points = Integer.parseInt(u.get("points"));
        int role = Integer.parseInt(u.get("role"));
        int ccn = Integer.parseInt(u.get("ccn"));
        int ppn = Integer.parseInt(u.get("ppn"));
        int userId = Integer.parseInt(u.get("userId"));


        User we = us.updateUser(userId, ccn, u.get("email"), u.get("firstName"), u.get("lastName"), u.get("password"), points, ppn, role, sick);

        return new ResponseEntity<>(we, HttpStatus.ACCEPTED);
    }

//    @GetMapping("/user/get/{email}")
//    public ResponseEntity<Object> handleGet(@PathVariable ("email") String email){
//        return ResponseEntity<>(us.findCurrentUserByEmail(email), HttpStatus.ACCEPTED);
//    }

    @GetMapping("/user/get/{id}")
    public ResponseEntity handleGet(@PathVariable ("id") int id){
        return new ResponseEntity<>(us.findCurrentUserById(id), HttpStatus.ACCEPTED);
    }

//    @GetMapping("/user/get")
//    public ResponseEntity<List> handleGetAllUsers() {
//        return new ResponseEntity<>(us.getAllUsers(), HttpStatus.ACCEPTED);
//
//    }
}




