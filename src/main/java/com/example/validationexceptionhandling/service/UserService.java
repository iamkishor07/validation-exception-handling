package com.example.validationexceptionhandling.service;

import com.example.validationexceptionhandling.Entity.User;
import com.example.validationexceptionhandling.dto.Userdto;

import java.util.List;

public interface UserService {

        public List<User> getAllUsersList();
        public User save(Userdto userdto);

         public User findById(Integer id);
        public void delete(Integer id);
}
