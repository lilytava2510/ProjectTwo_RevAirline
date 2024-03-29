package com.revature.repository;


import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> , CrudRepository<User, Integer>{

    //public List<User> findAll();
    public User findByEmailAndPassword(String email, String password);
    //public User createUser(String email, String password, int role);
    public User findByEmail(String email);

}
