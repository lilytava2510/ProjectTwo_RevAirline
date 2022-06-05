package com.revature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.Pilot;
import com.revature.repository.UserRepo;
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
import java.util.LinkedHashMap;
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
public class TestingUserService {

    @BeforeEach
    public void registrationBeforeTest(){
        MockitoAnnotations.openMocks(this);
    }

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
    @Mock
    static UserRepo ur;

    @InjectMocks
    static UserService us;

    @Test
    public void createUserTest(){
       User o = new User("@", "pass", 3, 2, "a", "b", 101, true, 111);
        when((ur).save(any())).thenReturn(o);
        //doNothing().when(ur).save(any());
        us.createUser("@", "pass", 3, 2, "a", "b", 101, true, 111);
        verify(ur).save(any());
    }

    @Test
    public void loginTest(){
        User o = new User();
        o.setEmail("pass");
        o.setPassword("test");
        when((ur).findByEmailAndPassword(any(),any())).thenReturn(o);
        User x = us.getUserByEmailAndPassword("pass","test");
        verify(ur).findByEmailAndPassword(any(),any());
        assertEquals("test", x.getPassword(),"passing");
    }

    @Test
    public void updateUserInfoTest(){
        User o = new User(1,101, "@", "a", "b", "pass", 3, 111, 2, true);
        when((ur).findByEmail(any())).thenReturn(o);
        User y = new User(1,101, "@", "a", "b", "test", 3, 111, 2, true);
        when((ur).saveAndFlush(any())).thenReturn(y);
        User x = us.updateUser(1,101, "@", "a", "b", "test", 3, 111, 2, true);
        verify(ur).saveAndFlush(any());
        assertEquals("test", x.getPassword(),"passing");
    }

    @Test
    public void findUserById(){
        Optional<User> o = Optional.of(new User(1, 101, "@", "a", "b", "pass", 3, 111, 2, true));
        when((ur).findById(anyInt())).thenReturn(o);
        User x = us.findCurrentUserById(1);
        verify(ur).findById(anyInt());
        assertEquals("pass", x.getPassword(),"passing");
    }

//
//    @BeforeEach
//    public void resetDB() {
//        ur.deleteAll();
//    }
//
//    private ObjectMapper om = new ObjectMapper();



}