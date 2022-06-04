package com.revature.services;


import com.revature.models.User;
import com.revature.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLOutput;
import java.util.List;

@Service
@Transactional
public class UserService {


    private UserRepo ur;


    @Autowired
    public UserService(UserRepo ur) {
        this.ur = ur;
    }

    public User createUser(String email, String password, int points, int role, String firstName, String lastName, int ccn, boolean sick, int ppn) {
        User u = new User(email, password, points, role, firstName, lastName, ccn, sick, ppn);
        User l = ur.findByEmail(email);
        if(l == null) {
            return ur.save(u);
        }else{return null;}
    }

    public User getUserByEmailAndPassword(String email, String password) {

        return ur.findByEmailAndPassword(email, password);

    }

    public User updateUser(int userid, int ccn, String email, String firstname, String lastname, String password, int points, int ppn, int role, boolean sick) {
        User u = new User(userid, ccn, email, firstname, lastname, password, points, ppn, role, sick);
        User l = ur.findByEmail(email);
        if(l == null ) {
            return ur.saveAndFlush(u);
        }else if(l.getUserId() == userid){return ur.saveAndFlush(u);}else{return null;}
    }

//    public User findCurrentUserByEmail(String email) {
//        return ur.findByEmail(email);
//    }

    public User findCurrentUserById(int id) {
        return ur.findById(id).get();
    }

    public List<User> getAllUsers(){
        return ur.findAll();
    }
}

