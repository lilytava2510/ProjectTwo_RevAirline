package com.revature.services;

import com.revature.models.City;
import com.revature.models.User;
import com.revature.models.Booking;
import com.revature.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingService {


    private com.revature.repository.BookingRepo br;

    private UserService us;

    private CityService cs;

    private UserRepo ur;

    @Autowired
    public BookingService(com.revature.repository.BookingRepo br, UserService us, CityService cs, UserRepo ur) {
        this.br = br;
        this.us = us;
        this.cs = cs;
        this.ur = ur;
    }

    public Booking createBooking(String date, double price, int userid, int origin, int destination) {






      User we = us.findCurrentUserById(userid);

      // System.out.println(we);

       City oc = cs.findCurrentCityById(origin);
       City dc = cs.findCurrentCityById(destination);
       Date ft =Date.valueOf(date);
        if(canBook(oc.getCity(), dc.getCity(), date)) {
            Booking b = new Booking(ft, price, we, oc, dc);

            return br.save(b);
        } else {
            return null;
        }
    }

    public Booking updateBooking(int bookingid, String date, double price, int userid, int origin, int destination) {






        User we = us.findCurrentUserById(userid);

        // System.out.println(we);

        City oc = cs.findCurrentCityById(origin);
        City dc = cs.findCurrentCityById(destination);
        Date ft =Date.valueOf(date);

        Booking b = new Booking(bookingid, ft, price, we, oc, dc);


       return br.saveAndFlush(b);
    }

    public void deleteBooking(int bookingid){
        Booking book = br.getById(bookingid);
        User u = us.getUserByEmailAndPassword(book.getUser().getEmail(), book.getUser().getPassword());
        List<Booking> ul = u.getBookingList();
        ul.remove(book);
        City originCity = cs.findCurrentCityById(book.getOrigin().getCityId());
        List<Booking> ol = originCity.getDepartures();
        ol.remove(book);
        originCity.setDepartures(ol);
        City destinationCity = cs.findCurrentCityById(book.getDestination().getCityId());
        List<Booking> dl = destinationCity.getArrivals();
        dl.remove(book);
        destinationCity.setArrivals(dl);
        for(Booking b : ul){
            if(b.getOrigin().getCityId() == originCity.getCityId()){
                b.setOrigin(originCity);}
            else if(b.getDestination().getCityId() == originCity.getCityId()){
                b.setDestination(originCity);}
            if(b.getOrigin().getCityId() == destinationCity.getCityId()){
                b.setOrigin(destinationCity);}
            else if (b.getDestination().getCityId() == destinationCity.getCityId()){
                b.setDestination(destinationCity);}

        }
        u.setBookingList(ul);




    }

//    public User getUserByEmailAndPassword(String email, String password) {
//
//        return ur.findByEmailAndPassword(email, password);
//
//    }
//
//    public User updateUser(int userid, int ccn, String email, String firstname, String lastname, String password, int points, int ppn, int role, boolean sick) {
//        User u = new User(userid, ccn, email, firstname, lastname, password, points, ppn, role, sick);
//        return ur.saveAndFlush(u);
//    }
//
////    public User findCurrentUserByEmail(String email) {
////        return ur.findByEmail(email);
////    }
//
    public List<Booking> findCurrentBookingByUId(int id) {
        User u = new User();
        u.setUserId(id);
        return br.findByUser(u);

    }

    public Boolean canBook(String origin, String destination, String date) {
        Date ft = Date.valueOf(date);
        List<Booking> b = br.findByDate(ft);
        int count = 0;
        City from = (City) cs.findCurrentCityByName(origin);
        City to = (City) cs.findCurrentCityByName(destination);
        for (Booking book : b) {
            if (book.getOrigin() == from && book.getDestination() == to) {
                count++;
            }
        }

        if (count < 10) {
            return true;
        } else {
            return false;
        }
    }
            // Helper Functions for Searching and Filtering
        public List<Booking> findCurrentBookingByDate(String date) {
            Date ft =Date.valueOf(date);
             List<Booking> b = br.findByDate(ft);
             return b;

        }

    public List<Booking> findCurrentBookingByOrigin(String origin) {
        City from = (City) cs.findCurrentCityByName(origin);
        List<Booking> b = br.findByOriginCity(from.getCityId());


        return b;

    }

    public List<Booking> findCurrentBookingByDestination(String destination) {
        City to = (City) cs.findCurrentCityByName(destination);
        List<Booking> b = br.findByDestinationCity(to.getCityId());


        return b;

    }


    public List<Booking> findCurrentBookingByDestination(String date, String origin, String destination) {
        City from = (City) cs.findCurrentCityByName(origin);
        Date ft =Date.valueOf(date);
        City to = (City) cs.findCurrentCityByName(destination);
        List<Booking> b = br.findByDateAndOriginAndDestination(ft, from.getCityId(), to.getCityId());


        return b;

    }
        

    }

