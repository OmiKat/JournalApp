package com.example.SpringMongoDb.Controller;


import com.example.SpringMongoDb.Service.UserService;
import com.example.SpringMongoDb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.createNewUser(user);
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        //security context holder contains all the security config we are using it to get username...
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();


        User userinDB = userService.findByUserName(userName);
        userinDB.setUserName(user.getUserName());
        userinDB.setPassword(user.getPassword());
        userService.updateUser(userinDB);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
