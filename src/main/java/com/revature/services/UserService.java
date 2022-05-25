package com.revature.services;


import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLOutput;

@Service
@Transactional
public class UserService {


    private com.revature.repository.UserRepo ur;


    @Autowired
    public UserService(com.revature.repository.UserRepo ur) {
        this.ur = ur;
    }

    public User createUser(String email, String password, int role) {
        User u = new User(email, password, role);

        return ur.save(u);
    }

    public User getUserByEmailandPassword(String email, String password) {

        User u = ur.findByEmailAndPassword(email, password);

        return u;


    }
}

