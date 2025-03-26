package com.example.SpringMongoDb.service;

import com.example.SpringMongoDb.Repo.UserDB;
import com.example.SpringMongoDb.Service.JournalService;
import com.example.SpringMongoDb.Service.UserService;
import com.example.SpringMongoDb.model.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {


    @Autowired
    UserService service;

    @Autowired
    UserDB repo;


    @Autowired
    JournalService journalService;

    @Test
    public void add(){
        User user = repo.findByuserName("omi");
        assertFalse(user.getJournalEntries().isEmpty());



    }
    @ParameterizedTest
    @CsvSource({
            //a,b,exp
            "1,1,2",
            "2,2,4",

    })
    public void test(int a , int b , int expected){
        assertEquals( expected ,a+b);
    }

}
