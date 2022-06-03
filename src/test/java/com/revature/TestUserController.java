package com.revature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.Pilot;
import com.revature.repository.UserRepo;
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
public class TestUserController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepo ur;

    @BeforeEach
    public void resetDB() {
        ur.deleteAll();
    }

    private ObjectMapper om = new ObjectMapper();

    @Test
    @Transactional
    public void successCreateUser() throws Exception{
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
                .andExpect(jsonPath("$.lastName").value("b")).andExpect(jsonPath("$.sick").value(true))
                .andExpect(jsonPath("$.ccn").value(101)).andExpect(jsonPath("$.ppn").value(111))
                .andExpect(jsonPath("$.points").value(3)).andExpect(jsonPath("$.role").value(2))
                .andExpect(jsonPath("$.userId").value(1));


    }

    @Test
    @Transactional
    public void successLoginUser() throws Exception{
        LinkedHashMap<String,String> registerBody = new LinkedHashMap<>();
        registerBody.put("email","@");
        registerBody.put("password","pass");

        User registeredUser = new User("@", "pass", 3, 2, "a", "b", 101, true, 111);
        ur.save(registeredUser);

        mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(registerBody))
                ).andDo(print()).andExpect(status().isAccepted()).andExpect(jsonPath("$.email").value("@"))
                .andExpect(jsonPath("$.password").value("pass")).andExpect(jsonPath("$.firstName").value("a"))
                .andExpect(jsonPath("$.lastName").value("b")).andExpect(jsonPath("$.sick").value(true))
                .andExpect(jsonPath("$.ccn").value(101)).andExpect(jsonPath("$.ppn").value(111))
                .andExpect(jsonPath("$.points").value(3)).andExpect(jsonPath("$.role").value(2))
                .andExpect(jsonPath("$.userId").value(2));

    }

    @Test
    @Transactional
    public void successUpdateUser() throws Exception{

        User registeredUser = new User("@", "pass", 3, 2, "a", "b", 101, true, 111);
        ur.save(registeredUser);

        LinkedHashMap<String,String> updateBody = new LinkedHashMap<>();
        updateBody.put("email","#");
        updateBody.put("password","test");
        updateBody.put("firstName","c");
        updateBody.put("lastName","d");
        updateBody.put("sick","false");
        updateBody.put("ccn","212");
        updateBody.put("ppn","222");
        updateBody.put("points","1");
        updateBody.put("role","4");
        updateBody.put("userId","3");

        mockMvc.perform(put("/user/update").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(updateBody))
                ).andDo(print()).andExpect(status().isAccepted()).andExpect(jsonPath("$.email").value("#"))
                .andExpect(jsonPath("$.password").value("test")).andExpect(jsonPath("$.firstName").value("c"))
                .andExpect(jsonPath("$.lastName").value("d")).andExpect(jsonPath("$.sick").value(false))
                .andExpect(jsonPath("$.ccn").value(212)).andExpect(jsonPath("$.ppn").value(222))
                .andExpect(jsonPath("$.points").value(1)).andExpect(jsonPath("$.role").value(4))
                .andExpect(jsonPath("$.userId").value(3));

    }

    @Test
    @Transactional
    public void successGetOneUser() throws Exception{


        User registeredUser = new User("@", "pass", 3, 2, "a", "b", 101, true, 111);
        ur.save(registeredUser);

        mockMvc.perform(get("/user/get/4")
                ).andDo(print()).andExpect(status().isAccepted()).andExpect(jsonPath("$.email").value("@"))
                .andExpect(jsonPath("$.password").value("pass")).andExpect(jsonPath("$.firstName").value("a"))
                .andExpect(jsonPath("$.lastName").value("b")).andExpect(jsonPath("$.sick").value(true))
                .andExpect(jsonPath("$.ccn").value(101)).andExpect(jsonPath("$.ppn").value(111))
                .andExpect(jsonPath("$.points").value(3)).andExpect(jsonPath("$.role").value(2))
                .andExpect(jsonPath("$.userId").value(4));

    }

    @Test
    @Transactional
    public void successGetAllUser() throws Exception{

        User registeredUser = new User("@", "pass", 3, 2, "a", "b", 101, true, 111);
        ur.save(registeredUser);
        User User = new User("?", "test", 3, 2, "c", "d", 101, true, 111);
        ur.save(User);

        mockMvc.perform(get("/user/get")
                ).andDo(print()).andExpect(status().isAccepted()).andExpect(jsonPath("$.[0].email").value("@"))
                .andExpect(jsonPath("$.[0].password").value("pass")).andExpect(jsonPath("$.[0].firstName").value("a"))
                .andExpect(jsonPath("$.[0].lastName").value("b")).andExpect(jsonPath("$.[0].sick").value(true))
                .andExpect(jsonPath("$.[0].ccn").value(101)).andExpect(jsonPath("$.[0].ppn").value(111))
                .andExpect(jsonPath("$.[0].points").value(3)).andExpect(jsonPath("$.[0].role").value(2))
                .andExpect(jsonPath("$.[0].userId").value(5)).andExpect(jsonPath("$.[1].email").value("?"))
                .andExpect(jsonPath("$.[1].password").value("test")).andExpect(jsonPath("$.[1].firstName").value("c"))
                .andExpect(jsonPath("$.[1].lastName").value("d")).andExpect(jsonPath("$.[1].sick").value(true))
                .andExpect(jsonPath("$.[1].ccn").value(101)).andExpect(jsonPath("$.[1].ppn").value(111))
                .andExpect(jsonPath("$.[1].points").value(3)).andExpect(jsonPath("$.[1].role").value(2))
                .andExpect(jsonPath("$.[1].userId").value(6));

    }

}