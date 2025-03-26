package com.example.SpringMongoDb.service;


import com.example.SpringMongoDb.Repo.UserDB;
import com.example.SpringMongoDb.Service.UserDetailServiceImp;
import com.example.SpringMongoDb.model.User;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
public class UserDetailServiceImpTest {

    @InjectMocks//inject all application context
    private UserDetailServiceImp userDetailServiceImp;

    @Mock
    private UserDB repo;


    private static AutoCloseable closeable;


    @BeforeEach
    public void setUp(){
     closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterAll
    public static void close() throws Exception {
        closeable.close();;
    }

    @Test
    void loadUserByUserName(){
        when(repo.findByuserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("omi").Roles(new ArrayList<>()).password("fasdasda").build());
        UserDetails user = userDetailServiceImp.loadUserByUsername("omi");
        assertNotNull(user);

    }

}
