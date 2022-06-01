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
public class TestingUserService {

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

        //   try {
        mockMvc.perform(post("/user/").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(registerBody))
                ).andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.email").value("@"))
                .andExpect(jsonPath("$.password").value("pass")).andExpect(jsonPath("$.firstName").value("a"))
                .andExpect(jsonPath("$.lastName").value("b")).andExpect(jsonPath("$.sick").value("true"))
                .andExpect(jsonPath("$.ccn").value("101")).andExpect(jsonPath("$.ppn").value("111"))
                .andExpect(jsonPath("$.points").value("3")).andExpect(jsonPath("$.role").value("2"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        User registeredUser = ur.findByEmail("@");

        assertEquals("@", registeredUser.getEmail());
        assertEquals("pass", registeredUser.getPassword());
        assertEquals("a", registeredUser.getFirstName());
        assertEquals("b", registeredUser.getLastName());
        assertEquals(101, registeredUser.getCcn());
        assertEquals(111, registeredUser.getPpn());
        assertEquals(3, registeredUser.getPoints());
        assertEquals(2, registeredUser.getRole());
        assertEquals(true, registeredUser.isSick());
        assertEquals(1, registeredUser.getUserId());

    }

    @Test
    @Transactional
    public void successLoginUser() throws Exception{
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

        //   try {
        mockMvc.perform(post("/user/").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(registerBody))
                ).andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.email").value("@"))
                .andExpect(jsonPath("$.password").value("pass")).andExpect(jsonPath("$.firstName").value("a"))
                .andExpect(jsonPath("$.lastName").value("b")).andExpect(jsonPath("$.sick").value("true"))
                .andExpect(jsonPath("$.ccn").value("101")).andExpect(jsonPath("$.ppn").value("111"))
                .andExpect(jsonPath("$.points").value("3")).andExpect(jsonPath("$.role").value("2"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        User registeredUser = ur.findByEmailAndPassword("@", "pass");

        assertEquals("@", registeredUser.getEmail());
        assertEquals("pass", registeredUser.getPassword());
        assertEquals("a", registeredUser.getFirstName());
        assertEquals("b", registeredUser.getLastName());
        assertEquals(101, registeredUser.getCcn());
        assertEquals(111, registeredUser.getPpn());
        assertEquals(3, registeredUser.getPoints());
        assertEquals(2, registeredUser.getRole());
        assertEquals(true, registeredUser.isSick());
        assertEquals(2, registeredUser.getUserId());

    }

    @Test
    @Transactional
    public void successUpdateUser() throws Exception{
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
                .andExpect(jsonPath("$.lastName").value("d")).andExpect(jsonPath("$.sick").value("false"))
                .andExpect(jsonPath("$.ccn").value("212")).andExpect(jsonPath("$.ppn").value("222"))
                .andExpect(jsonPath("$.points").value("1")).andExpect(jsonPath("$.role").value("4")).andExpect(jsonPath("$.userId").value("3"));

        User registeredUser = ur.findById(3).get();

        assertEquals("#", registeredUser.getEmail());
        assertEquals("test", registeredUser.getPassword());
        assertEquals("c", registeredUser.getFirstName());
        assertEquals("d", registeredUser.getLastName());
        assertEquals(212, registeredUser.getCcn());
        assertEquals(222, registeredUser.getPpn());
        assertEquals(1, registeredUser.getPoints());
        assertEquals(4, registeredUser.getRole());
        assertEquals(false, registeredUser.isSick());
        assertEquals(3, registeredUser.getUserId());

    }

}