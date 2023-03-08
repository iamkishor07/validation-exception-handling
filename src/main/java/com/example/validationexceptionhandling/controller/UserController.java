package com.example.validationexceptionhandling.controller;

import com.example.validationexceptionhandling.Entity.User;
import com.example.validationexceptionhandling.dto.Userdto;
import com.example.validationexceptionhandling.exception.UserNotFoundException;
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

    @PostMapping("/signup") //adds the user to the DB
    public  ResponseEntity<User> Saveuser(@RequestBody  @Valid Userdto userdto)  {
           return new ResponseEntity<>(userServiceImpl.save(userdto), HttpStatus.CREATED);

    }

   @GetMapping("/fetchAll")//fetchs all users form the DB
   public ResponseEntity<List<User>>getAllUsers(){
        return  ResponseEntity.ok(userServiceImpl.getAllUsersList());
    }

    @GetMapping("/{id}")//fetchs specified users based on id else throws userdefinedexception
    public  ResponseEntity<User> getuser(@PathVariable int id) throws UserNotFoundException {
        return new ResponseEntity<>(userServiceImpl.findById(id),HttpStatus.FOUND);
    }


    @PutMapping("/{id}") //this updated the users data with the specified user id or else throws userdefindexception
    public  ResponseEntity<User> updateuser(@RequestBody Userdto userdto,@PathVariable int id) throws UserNotFoundException {
        return   ResponseEntity.ok(userServiceImpl.update(userdto,id));

    }

    @DeleteMapping("/{id}")//It deletes the user with the given/specified id or else throws userdefinedexception
    public ResponseEntity<String> deleteUser(@PathVariable int id) throws UserNotFoundException {
        userServiceImpl.delete(id);
        return ResponseEntity.ok("Deleted the user");
    }
}
