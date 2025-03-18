package com.example.SpringMongoDb.Controller;

import com.example.SpringMongoDb.Service.JournalService;
import com.example.SpringMongoDb.Service.UserService;
import com.example.SpringMongoDb.model.MyJournal;
import com.example.SpringMongoDb.model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {


    @Autowired
    JournalService service;

    @Autowired
    UserService userService;


    @GetMapping("/{username}")
    public ResponseEntity<?> getAllEntriesByUsers(@PathVariable String username){
        User user = userService.findByUserName(username);
        List<MyJournal> all = user.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/{username}")
    public ResponseEntity<MyJournal> createEntry(@RequestBody MyJournal journal,@PathVariable String username){
        try {
            journal.setDate(LocalDateTime.now());
            service.createEntry(journal,username);
            return new ResponseEntity<>(journal, HttpStatus.CREATED);
        }
        catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{id}")
    public MyJournal getEntryBYId(@PathVariable ObjectId id){
        return service.getEntryBYid(id).orElse(null);

    }

    @DeleteMapping("/id/{username}/{id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId id, @PathVariable String username){
        service.deletebyId(id,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{username}/{id}")
    public ResponseEntity<?> updateEntityByID(@PathVariable ObjectId id ,
                                              @RequestBody MyJournal entry,
                                              @PathVariable String username
    ){
        MyJournal old = service.getEntryBYid(id).orElse(null);
        if(old != null) {
            old.setTitle(entry.getTitle() != null && !entry.getTitle().isEmpty() ? entry.getTitle() : old.getTitle());
            old.setContent(entry.getContent() != null && !entry.getContent().isEmpty() ? entry.getContent() : old.getContent());
            service.createEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
