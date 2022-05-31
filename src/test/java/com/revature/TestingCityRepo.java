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
    public void successCreateCity() throws Exception{
        LinkedHashMap<String,String> registerBody = new LinkedHashMap<>();
        registerBody.put("city","la");
        registerBody.put("position","20");

     //   try {
            mockMvc.perform(post("/city/").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(registerBody))
            ).andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.city").value("la"))
                    .andExpect(jsonPath("$.position").value("20"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        City registeredCity = (City)cr.findByCity("la");

        assertEquals("la", registeredCity.getCity());
        assertEquals(20, registeredCity.getPosition());

    }

    @Test
    @Transactional
    public void successFindCityId() throws Exception{
        LinkedHashMap<String,String> registerBody = new LinkedHashMap<>();
        registerBody.put("city","la");
        registerBody.put("position","20");

        //   try {
        mockMvc.perform(post("/city/").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(registerBody))
                ).andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.city").value("la"))
                .andExpect(jsonPath("$.position").value("20"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        City registeredCity = cr.findById(2).get();

        assertEquals("la", registeredCity.getCity());
        assertEquals(20, registeredCity.getPosition());

    }

}
