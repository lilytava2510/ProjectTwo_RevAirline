package com.revature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.City;
import com.revature.repository.CityRepo;
import com.revature.Pilot;
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
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Pilot.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class TestingCityRepo {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CityRepo cr;

    @BeforeEach
    public void resetDB(){
        cr.deleteAll();
    }

    private ObjectMapper om = new ObjectMapper();
    @Test
    @Transactional
    public void successFindCityId() throws Exception{

        City test = new City("la", 20);
        cr.save(test);

        City registeredCity = cr.findById(1).get();

        assertEquals("la", registeredCity.getCity());
        assertEquals(20, registeredCity.getPosition());

    }

}
