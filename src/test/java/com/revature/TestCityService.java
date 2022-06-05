package com.revature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Booking;
import com.revature.models.City;
import com.revature.models.User;
import com.revature.Pilot;
import com.revature.repository.CityRepo;
import com.revature.repository.UserRepo;
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
public class TestCityService {

    @BeforeEach
    public void registrationBeforeTest(){
        MockitoAnnotations.openMocks(this);
    }

    //    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
    @Mock
    static CityRepo cr;

    @InjectMocks
    static CityService cs;

    @Test
    public void createCityTest(){
        City ny = new City("ny", 4);
        when((cr).save(any())).thenReturn(ny);
        when((cr).findByCity(any())).thenReturn(null);
        when((cr).findByPosition(anyInt())).thenReturn(null);
        City x = cs.createCity(ny);
        verify(cr).save(any());
        assertEquals("ny", x.getCity(),"passing");
    }

    @Test
    public void findCurrentCityByyNameTest(){
        City ny = new City("ny", 4);
        when((cr).findByCity(any())).thenReturn(ny);
        City x = cs.findCurrentCityByName("ny");
        verify(cr).findByCity(any());
        assertEquals("ny", x.getCity(),"passing");
    }

    @Test
    public void getAllCities(){
        List<City> bl = new ArrayList<>();
        City ny = new City("ny", 4);
        bl.add(ny);
        when((cr).findAll()).thenReturn(bl);
        ArrayList<City> x = (ArrayList<City>) cs.getAllCities();
        verify(cr).findAll();
        assertEquals("ny", x.get(0).getCity(),"passing");
    }

}
