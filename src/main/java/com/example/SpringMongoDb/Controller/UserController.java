package com.example.SpringMongoDb.Controller;


import com.example.SpringMongoDb.Service.UserService;
import com.example.SpringMongoDb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.createNewUser(user);
    }


    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user , @PathVariable String  userName){
        User userinDB = userService.findByUserName(userName);
        if(userinDB != null){
            userinDB.setUserName(user.getUserName());
            userinDB.setPassword(user.getPassword());

            userService.updateUser(userinDB);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

//    @DeleteMapping
//    public ResponseEntity<?> deleteUser(@RequestBody User user){
//
//    }




}
