package com.revature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.Pilot;
import com.revature.repository.BookingRepo;
import com.revature.repository.UserRepo;
import com.revature.services.BookingService;
import com.revature.services.CityService;
import com.revature.services.UserService;
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.revature.models.City;
import com.revature.repository.CityRepo;
import com.revature.models.Booking;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Pilot.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class TestingBookingService {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepo ur;

    @Autowired
    private CityRepo cr;

    @Autowired
    private CityService cs;

    @Autowired
    private BookingRepo br;

    @Autowired
    private BookingService bs;

    @Autowired
    private UserService us;

    @BeforeEach
    public void resetDB() {
        br.deleteAll();
        ur.deleteAll();
        cr.deleteAll();
    }

    private ObjectMapper om = new ObjectMapper();

    @Test
    @Transactional
    public void successCreateBooking() throws Exception{
        LinkedHashMap<String,String> registerBody = new LinkedHashMap<>();
        registerBody.put("email","@");
        registerBody.put("password","pass");
        registerBody.put("firstName","a");
        registerBody.put("lastName","b");
        registerBody.put("sick","true");
        registerBody.put("ccn","101");
        registerBody.put("ppn","111");
        registerBody.put("points","3");
        registerBody.put("role","2");

        mockMvc.perform(post("/user/").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(registerBody))
                ).andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.email").value("@"))
                .andExpect(jsonPath("$.password").value("pass")).andExpect(jsonPath("$.firstName").value("a"))
                .andExpect(jsonPath("$.lastName").value("b")).andExpect(jsonPath("$.sick").value("true"))
                .andExpect(jsonPath("$.ccn").value("101")).andExpect(jsonPath("$.ppn").value("111"))
                .andExpect(jsonPath("$.points").value("3")).andExpect(jsonPath("$.role").value("2"));

        LinkedHashMap<String,String> toBody = new LinkedHashMap<>();
        toBody.put("city","la");
        toBody.put("position","20");

        mockMvc.perform(post("/city/").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(toBody))
                ).andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.city").value("la"))
                .andExpect(jsonPath("$.position").value("20"));

        City ny = new City("ny",30);
        cr.save(ny);


        LinkedHashMap<String,String> bookingBody = new LinkedHashMap<>();
        bookingBody.put("date","1111-12-12");
        bookingBody.put("userId","1");
        bookingBody.put("origin","ny");
        bookingBody.put("destination","la");


        mockMvc.perform(post("/booking/").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(bookingBody))
                ).andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.date").value("1111-12-12"))
                .andExpect(jsonPath("$.bookingid").value(4)).andExpect(jsonPath("$.price").value(30.0))
                .andExpect(jsonPath("$.origin.city").value("ny")).andExpect(jsonPath("$.origin.position").value(30)).andExpect(jsonPath("$.origin.cityId").value(3))
                .andExpect(jsonPath("$.destination.city").value("la")).andExpect(jsonPath("$.destination.cityId").value(2)).andExpect(jsonPath("$.destination.position").value(20));
//        .andExpect(jsonPath("$.user.userId").value("1")).andExpect(jsonPath("$.user.email").value("@"))
//                .andExpect(jsonPath("$.user.password").value("pass")).andExpect(jsonPath("$.user.points").value(3))
//                .andExpect(jsonPath("$.user.role").value(2))
//                .andExpect(jsonPath("$.user.firstName").value("a")).andExpect(jsonPath("$.user.lastName").value("b"))
//                .andExpect(jsonPath("$.user.ccn").value(101)).andExpect(jsonPath("$.user.ppn").value(111))
//                .andExpect(jsonPath("$.user.sick").value(true))

        List<Booking> bookingUser = bs.findCurrentBookingByUId(1);

//        assertEquals("@", bookingUser.get(0).getUser().getEmail());
//        assertEquals("pass", bookingUser.get(0).getUser().getPassword());
//        assertEquals("a", bookingUser.get(0).getUser().getFirstName());
//        assertEquals("b", bookingUser.get(0).getUser().getLastName());
//        assertEquals(101, bookingUser.get(0).getUser().getCcn());
//        assertEquals(111, bookingUser.get(0).getUser().getPpn());
//        assertEquals(3, bookingUser.get(0).getUser().getPoints());
//        assertEquals(2, bookingUser.get(0).getUser().getRole());
//        assertEquals(true, bookingUser.get(0).getUser().isSick());
//        assertEquals(1, bookingUser.get(0).getUser().getUserId());
        assertEquals(3, bookingUser.get(0).getOrigin().getCityId());
        assertEquals(30, bookingUser.get(0).getOrigin().getPosition());
        assertEquals("ny", bookingUser.get(0).getOrigin().getCity());
        assertEquals(2, bookingUser.get(0).getDestination().getCityId());
        assertEquals(20, bookingUser.get(0).getDestination().getPosition());
        assertEquals("la", bookingUser.get(0).getDestination().getCity());
        assertEquals(4, bookingUser.get(0).getBookingid());
        assertEquals("1111-12-12", bookingUser.get(0).getDate().toString());
        assertEquals(30.0, bookingUser.get(0).getPrice());

    }

    @Test
    @Transactional
    public void successGetBooking() throws Exception {

        User first = us.createUser("@", "pass", 1, 2, "a", "b", 123, true, 321);
        City la = new City("la",3);
        City holder = cs.createCity(la);
        City ny = new City("ny", 4);
        City temp = cs.createCity(ny);
        Booking record = bs.createBooking("1111-12-12", 1, "ny", "la");

        mockMvc.perform(get("/booking/get/1")
                ).andDo(print()).andExpect(status().isAccepted()).andExpect(jsonPath("$.[0].date").value("1111-12-12"))
                .andExpect(jsonPath("$.[0].bookingid").value(4)).andExpect(jsonPath("$.[0].price").value(3.0))
                .andExpect(jsonPath("$.[0].origin.city").value("ny")).andExpect(jsonPath("$.[0].origin.position").value(4)).andExpect(jsonPath("$.[0].origin.cityId").value(3))
                .andExpect(jsonPath("$.[0].destination.city").value("la")).andExpect(jsonPath("$.[0].destination.cityId").value(2)).andExpect(jsonPath("$.[0].destination.position").value(3));




//        assertEquals("@", bookingUser.get(0).getUser().getEmail());
//        assertEquals("pass", bookingUser.get(0).getUser().getPassword());
//        assertEquals("a", bookingUser.get(0).getUser().getFirstName());
//        assertEquals("b", bookingUser.get(0).getUser().getLastName());
//        assertEquals(123, bookingUser.get(0).getUser().getCcn());
//        assertEquals(321, bookingUser.get(0).getUser().getPpn());
//        assertEquals(1, bookingUser.get(0).getUser().getPoints());
//        assertEquals(2, bookingUser.get(0).getUser().getRole());
//        assertEquals(true, bookingUser.get(0).getUser().isSick());
//        assertEquals(1, bookingUser.get(0).getUser().getUserId());
        assertEquals(3, record.getOrigin().getCityId());
        assertEquals(4, record.getOrigin().getPosition());
        assertEquals("ny", record.getOrigin().getCity());
        assertEquals(2, record.getDestination().getCityId());
        assertEquals(3, record.getDestination().getPosition());
        assertEquals("la", record.getDestination().getCity());
        assertEquals(4, record.getBookingid());
        assertEquals("1111-12-12", record.getDate().toString());
        assertEquals(3.0, record.getPrice());
    }

    @Test
    @Transactional
    public void successPutBooking() throws Exception {

        User first = us.createUser("@", "pass", 1, 2, "a", "b", 123, true, 321);
        City la = new City("la",3);
        City holder = cs.createCity(la);
        City ny = new City("ny", 4);
        City temp = cs.createCity(ny);
        Booking record = bs.createBooking("1111-12-12", 1, "ny", "la");
        LinkedHashMap<String,String> registerBody = new LinkedHashMap<>();
        registerBody.put("price","3");
        registerBody.put("bookingid","4");
        registerBody.put("date","2222-02-02");
        registerBody.put("userId","1");
        registerBody.put("origin","la");
        registerBody.put("destination","ny");


        mockMvc.perform(put("/booking/update").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(registerBody))
                ).andDo(print()).andExpect(status().isAccepted()).andExpect(jsonPath("$.date").value("2222-02-02"))
                .andExpect(jsonPath("$.bookingid").value(4)).andExpect(jsonPath("$.price").value(3.0))
                .andExpect(jsonPath("$.origin.city").value("la")).andExpect(jsonPath("$.origin.position").value(3)).andExpect(jsonPath("$.origin.cityId").value(2))
                .andExpect(jsonPath("$.destination.city").value("ny")).andExpect(jsonPath("$.destination.cityId").value(3)).andExpect(jsonPath("$.destination.position").value(4));




//        assertEquals("@", bookingUser.get(0).getUser().getEmail());
//        assertEquals("pass", bookingUser.get(0).getUser().getPassword());
//        assertEquals("a", bookingUser.get(0).getUser().getFirstName());
//        assertEquals("b", bookingUser.get(0).getUser().getLastName());
//        assertEquals(123, bookingUser.get(0).getUser().getCcn());
//        assertEquals(321, bookingUser.get(0).getUser().getPpn());
//        assertEquals(1, bookingUser.get(0).getUser().getPoints());
//        assertEquals(2, bookingUser.get(0).getUser().getRole());
//        assertEquals(true, bookingUser.get(0).getUser().isSick());
//        assertEquals(1, bookingUser.get(0).getUser().getUserId());
//        assertEquals(3, record.getOrigin().getCityId());
//        assertEquals(4, record.getOrigin().getPosition());
//        assertEquals("ny", record.getOrigin().getCity());
//        assertEquals(2, record.getDestination().getCityId());
//        assertEquals(3, record.getDestination().getPosition());
//        assertEquals("la", record.getDestination().getCity());
//        assertEquals(4, record.getBookingid());
//        assertEquals("1111-12-12", record.getDate().toString());
//        assertEquals(3.0, record.getPrice());
    }

    @Test
    @Transactional
    public void successDeleteBooking() throws Exception {

        User first = us.createUser("@", "pass", 1, 2, "a", "b", 123, true, 321);
        City la = new City("la", 3);
        City holder = cs.createCity(la);
        City ny = new City("ny", 4);
        City temp = cs.createCity(ny);
        Booking record = bs.createBooking("1111-12-12", 1, "ny", "la");



        mockMvc.perform(delete("/booking/update/4")
                ).andDo(print()).andExpect(status().isNotFound());

        //List<Booking> bookingUser = bs.findCurrentBookingByUId(1);

    }
    }
