package com.example.SpringMongoDb.Service;

import com.example.SpringMongoDb.Repo.UserDB;
import com.example.SpringMongoDb.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserDB repo;

    public List<User> getAll() {
       return repo.findAll();
    }


    public void createUser(User user) {
        repo.save(user);
    }


    public Optional<User> getEntryBYid(ObjectId id) {
        return repo.findById(id);
    }

    public void deletebyId(ObjectId id) {
        repo.deleteById(id);
    }

    public User updateUser(User entry) {
        return repo.save(entry);
    }

    public User findByUserName(String userName) {
        return repo.findByuserName(userName);
    }
}
