package com.revature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.City;
import com.revature.repository.CityRepo;
import com.revature.Pilot;
import com.revature.services.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Pilot.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class TestCityController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CityRepo cr;

    @Autowired
    private CityService cs;

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



        mockMvc.perform(post("/city/").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(registerBody))
                ).andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.city").value("la"))
                .andExpect(jsonPath("$.position").value(20)).andExpect(jsonPath("$.cityId").value(1));




    }

    @Test
    @Transactional
    public void failedCreateCity() throws Exception{
        LinkedHashMap<String,String> registerBody = new LinkedHashMap<>();
        registerBody.put("city","la");
        registerBody.put("position","20");
        City la = new City("la",3);
        City holder = cs.createCity(la);
        City ny = new City("ny", 4);
        City temp = cs.createCity(ny);

        MvcResult content = mockMvc.perform(post("/city/").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(registerBody))
        ).andDo(print()).andExpect(status().isCreated()).andReturn();
        String result = content.getResponse().getContentAsString();
        assertEquals("",result);
    }

    @Test
    @Transactional
    public void successGetCity() throws Exception{
        City la = new City("la",3);
        City holder = cs.createCity(la);
        City ny = new City("ny", 4);
        City temp = cs.createCity(ny);

        mockMvc.perform(get("/city/get")
                ).andDo(print()).andExpect(status().isAccepted()).andExpect(jsonPath("$.[0].city").value("la"))
                .andExpect(jsonPath("$.[0].position").value(3)).andExpect(jsonPath("$.[0].cityId").value(1))
                .andExpect(jsonPath("$.[1].city").value("ny"))
                .andExpect(jsonPath("$.[1].position").value(4)).andExpect(jsonPath("$.[1].cityId").value(2));




    }

}