package com.example.validationexceptionhandling.service;

import com.example.validationexceptionhandling.Entity.User;
import com.example.validationexceptionhandling.dto.Userdto;
import com.example.validationexceptionhandling.exception.UserNotFoundException;

import java.util.List;
public interface UserService {

         List<User> getAllUsersList();
         User save(Userdto userdto) ;
         User findById(Integer id) throws UserNotFoundException;
         void delete(Integer id) throws UserNotFoundException;
         User update(Userdto userdto, Integer id) throws UserNotFoundException;
}
