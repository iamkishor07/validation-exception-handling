package com.example.validationexceptionhandling.controller;

import com.example.validationexceptionhandling.Entity.User;
import com.example.validationexceptionhandling.dto.Userdto;
import com.example.validationexceptionhandling.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private  UserServiceImpl userServiceImpl;

    @PostMapping("/signup")
    public  ResponseEntity<User> Saveuser(@RequestBody  @Valid Userdto userdto){
           return new ResponseEntity<>(userServiceImpl.save(userdto), HttpStatus.CREATED);

    }

   @GetMapping("/fetchAll")
   public ResponseEntity<List<User>>getAllUsers(){
        return  ResponseEntity.ok(userServiceImpl.getAllUsersList());
    }

    @GetMapping("/{id}") public  ResponseEntity<User> getuser(@PathVariable int id){
        return ResponseEntity.ok(userServiceImpl.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        userServiceImpl.delete(id);
        return ResponseEntity.ok("Deleted the user");
    }
}
