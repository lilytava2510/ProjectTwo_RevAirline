package com.revature.repository;

import com.revature.models.Booking;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> , CrudRepository<Booking, Integer> {
    List<Booking> findByUser(User u);

    List<Booking> findByDate(Date date);

    List<Booking> findByOriginCity(int cityId);

    List<Booking> findByDestinationCity(int cityId);

    List<Booking> findByDateAndOriginAndDestination(Date ft, int cityId, int cityId1);


    //public List<User> findAll();
   // public User findByEmailAndPassword(String email, String password);
    //public User createUser(String email, String password, int role);
   // public User findByEmail(String email);
}
