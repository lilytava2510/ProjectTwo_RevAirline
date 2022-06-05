package com.revature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Booking;
import com.revature.models.City;
import com.revature.models.User;
import com.revature.Pilot;
import com.revature.repository.BookingRepo;
import com.revature.repository.CityRepo;
import com.revature.repository.UserRepo;
import com.revature.services.BookingService;
import com.revature.services.CityService;
import com.revature.services.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

    //@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Pilot.class)
//@AutoConfigureMockMvc
//@AutoConfigureTestDatabase
//@ActiveProfiles("test")
    public class TestingBookingService {

        @BeforeEach
        public void registrationBeforeTest(){
            MockitoAnnotations.openMocks(this);
        }

        //    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
        @Mock
        static BookingRepo br;

        @InjectMocks
        static BookingService bs;

        @Mock
        static CityRepo cr;

        @InjectMocks
        static CityService cs;

        @Mock
        static UserRepo ur;

        @InjectMocks
        static UserService us;

        @Test
        public void createBookingTest(){
            Optional<User> x = Optional.of(new User(1, 101, "@", "a", "b", "pass", 3, 111, 2, true));
            User o = new User(1,101, "@", "a", "b", "pass", 3, 111, 2, true);
            City ny = new City("ny", 4);
            Date ft =Date.valueOf("1111-02-02");
            List<Booking> bl = new ArrayList<>();
            Booking b =new Booking(1 , ft, 0.0, o, ny, ny);
            when((ur).findById(anyInt())).thenReturn(x);
            when((cr).findByCity(any())).thenReturn(ny);
            when((br).findByDate(any())).thenReturn(bl);
            when((br).saveAndFlush(any())).thenReturn(b);
            //doNothing().when(ur).save(any());
            Booking book =bs.createBooking("1111-02-02",1,"ny", "ny");
            verify(br).saveAndFlush(any());
            assertEquals("1", book.getBookingid(),"passing");
        }

        @Test
        public void canBookTest(){
            List<Booking> bl = new ArrayList<>();
            City ny = new City("ny", 4);
            when((cr).findByCity(any())).thenReturn(ny);
            when((br).findByDate(any())).thenReturn(bl);
            boolean check = bs.canBook("ny","ny","1111-02-02");
            verify(br).findByDate(any());
            assertEquals(true, check,"passing");
        }

//        @Test
//        public void updateUserInfoTest(){
//            User o = new User(1,101, "@", "a", "b", "pass", 3, 111, 2, true);
//            when((ur).findByEmail(any())).thenReturn(o);
//            User y = new User(1,101, "@", "a", "b", "test", 3, 111, 2, true);
//            when((ur).saveAndFlush(any())).thenReturn(y);
//            User x = us.updateUser(1,101, "@", "a", "b", "test", 3, 111, 2, true);
//            verify(ur).saveAndFlush(any());
//            assertEquals("test", x.getPassword(),"passing");
//        }

        @Test
        public void findCurrentBookingByUIdTest(){
            City ny = new City("ny", 4);
            Date ft =Date.valueOf("1111-02-02");
            List<Booking> bl = new ArrayList<>();
            User o = new User(1, 101, "@", "a", "b", "pass", 3, 111, 2, true);
            Booking b =new Booking(1 , ft, 0.0, o, ny, ny);
            bl.add(b);
            o.setBookingList(bl);
            when((br).findByUser(any())).thenReturn(bl);
            ArrayList<Booking> x = (ArrayList<Booking>) bs.findCurrentBookingByUId(1);
            verify(br).findByUser(any());
            assertEquals(1, x.get(0).getBookingid(),"passing");
        }

        @Test
        public void createBookingPointsTest(){
            Optional<User> x = Optional.of(new User(1, 101, "@", "a", "b", "pass", 3, 111, 2, true));
            User o = new User(1,101, "@", "a", "b", "pass", 3, 111, 2, true);
            City ny = new City("ny", 4);
            Date ft =Date.valueOf("1111-02-02");
            List<Booking> bl = new ArrayList<>();
            Booking b =new Booking(1 , ft, 0.0, o, ny, ny);
            when((ur).findById(anyInt())).thenReturn(x);
            when((cr).findByCity(any())).thenReturn(ny);
            when((br).findByDate(any())).thenReturn(bl);
            when((br).save(any())).thenReturn(b);
            //doNothing().when(ur).save(any());
            Booking book = bs.createBookingPoints("1111-02-02",1,"ny", "ny");
            verify(br).save(any());
            assertEquals("1", book.getBookingid(),"passing");
        }




    }
