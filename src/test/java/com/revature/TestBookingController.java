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
import org.springframework.test.web.servlet.MvcResult;
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
public class TestBookingController {

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

        User registeredUser = new User("@", "pass", 3, 2, "a", "b", 101, true, 111);
        ur.save(registeredUser);

        City la = new City("la", 20);
        cr.save(la);

        City ny = new City("ny",30);
        cr.save(ny);

        LinkedHashMap<String,String> bookingBody = new LinkedHashMap<>();
        bookingBody.put("date","1111-12-12");
        bookingBody.put("userId","5");
        bookingBody.put("origin","ny");
        bookingBody.put("destination","la");

        mockMvc.perform(post("/booking/").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(bookingBody))
                ).andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.date").value("1111-12-12"))
                .andExpect(jsonPath("$.bookingid").value(8)).andExpect(jsonPath("$.price").value(30.0))
                .andExpect(jsonPath("$.origin.city").value("ny")).andExpect(jsonPath("$.origin.position").value(30)).andExpect(jsonPath("$.origin.cityId").value(7))
                .andExpect(jsonPath("$.destination.city").value("la")).andExpect(jsonPath("$.destination.cityId").value(6)).andExpect(jsonPath("$.destination.position").value(20));

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

    }

    @Test
    @Transactional
    public void successPutBooking() throws Exception {

        User first = us.createUser("@", "pass", 1, 2, "a", "b", 123, true, 321);
        City la = new City("la",3);
        City holder = cs.createCity(la);
        City ny = new City("ny", 4);
        City temp = cs.createCity(ny);
        Booking record = bs.createBooking("1111-12-12", 32, "ny", "la");
        LinkedHashMap<String,String> registerBody = new LinkedHashMap<>();
        registerBody.put("price","3");
        registerBody.put("bookingid","35");
        registerBody.put("date","2222-02-02");
        registerBody.put("userId","32");
        registerBody.put("origin","la");
        registerBody.put("destination","ny");

        mockMvc.perform(put("/booking/update").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(registerBody))
                ).andDo(print()).andExpect(status().isAccepted()).andExpect(jsonPath("$.date").value("2222-02-02"))
                .andExpect(jsonPath("$.bookingid").value(35)).andExpect(jsonPath("$.price").value(3.0))
                .andExpect(jsonPath("$.origin.city").value("la")).andExpect(jsonPath("$.origin.position").value(3)).andExpect(jsonPath("$.origin.cityId").value(33))
                .andExpect(jsonPath("$.destination.city").value("ny")).andExpect(jsonPath("$.destination.cityId").value(34)).andExpect(jsonPath("$.destination.position").value(4));

    }

    @Test
    @Transactional
    public void successDeleteBooking() throws Exception {

        User first = us.createUser("@", "pass", 1, 2, "a", "b", 123, true, 321);
        City la = new City("la", 3);
        City holder = cs.createCity(la);
        City ny = new City("ny", 4);
        City temp = cs.createCity(ny);
        Booking record = bs.createBooking("1111-12-12", 22, "ny", "la");

        mockMvc.perform(delete("/booking/update/12")
        ).andDo(print()).andExpect(status().isNotFound());

    }

    @Test
    @Transactional
    public void successGetPrice() throws Exception {
        LinkedHashMap<String,String> registerBody = new LinkedHashMap<>();
        registerBody.put("date","2222-02-02");
        registerBody.put("origin","la");
        registerBody.put("destination","ny");
        City la = new City("la",3);
        City holder = cs.createCity(la);
        City ny = new City("ny", 4);
        City temp = cs.createCity(ny);

         mockMvc.perform(post("/booking/price").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(registerBody))
                ).andDo(print()).andExpect(status().isAccepted()).andExpect(jsonPath("$.date").value("2222-02-02"))
                .andExpect(jsonPath("$.price").value(3.0))
                .andExpect(jsonPath("$.origin.city").value("la")).andExpect(jsonPath("$.origin.position").value(3)).andExpect(jsonPath("$.origin.cityId").value(30))
                .andExpect(jsonPath("$.destination.city").value("ny")).andExpect(jsonPath("$.destination.cityId").value(31)).andExpect(jsonPath("$.destination.position").value(4));

    }

    @Test
    @Transactional
    public void failedGetPrice() throws Exception {
        LinkedHashMap<String,String> registerBody = new LinkedHashMap<>();
        registerBody.put("date","1111-12-12");
        registerBody.put("origin","ny");
        registerBody.put("destination","la");
        User registeredUser = new User("@", "pass", 1000, 2, "a", "b", 101, true, 111);
        ur.save(registeredUser);
        City la = new City("la",3);
        City holder = cs.createCity(la);
        City ny = new City("ny", 4);
        City temp = cs.createCity(ny);
        Booking record = bs.createBooking("1111-12-12", 9, "ny", "la");
        record = bs.createBooking("1111-12-12", 9, "ny", "la");
        record = bs.createBooking("1111-12-12", 9, "ny", "la");
        record = bs.createBooking("1111-12-12", 9, "ny", "la");
        record = bs.createBooking("1111-12-12", 9, "ny", "la");
        record = bs.createBooking("1111-12-12", 9, "ny", "la");
        record = bs.createBooking("1111-12-12", 9, "ny", "la");
        record = bs.createBooking("1111-12-12", 9, "ny", "la");
        record = bs.createBooking("1111-12-12", 9, "ny", "la");
        record = bs.createBooking("1111-12-12", 9, "ny", "la");

        MvcResult content = mockMvc.perform(post("/booking/price").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(registerBody))
        ).andDo(print()).andExpect(status().isAccepted()).andReturn();
        String result = content.getResponse().getContentAsString();
        assertEquals("",result);
    }

    @Test
    @Transactional
    public void successPointsBooking() throws Exception{

        User registeredUser = new User("@", "pass", 1000, 2, "a", "b", 101, true, 111);
        ur.save(registeredUser);

        City la = new City("la", 20);
        cr.save(la);

        City ny = new City("ny",30);
        cr.save(ny);

        LinkedHashMap<String,String> bookingBody = new LinkedHashMap<>();
        bookingBody.put("date","1111-12-12");
        bookingBody.put("userId","26");
        bookingBody.put("origin","ny");
        bookingBody.put("destination","la");

        mockMvc.perform(post("/booking/points").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(bookingBody))
                ).andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.date").value("1111-12-12"))
                .andExpect(jsonPath("$.bookingid").value(29)).andExpect(jsonPath("$.price").value(0.0))
                .andExpect(jsonPath("$.origin.city").value("ny")).andExpect(jsonPath("$.origin.position").value(30)).andExpect(jsonPath("$.origin.cityId").value(28))
                .andExpect(jsonPath("$.destination.city").value("la")).andExpect(jsonPath("$.destination.cityId").value(27)).andExpect(jsonPath("$.destination.position").value(20));
            registeredUser = ur.findByEmail("@");
            assertEquals(970, registeredUser.getPoints());
    }



}