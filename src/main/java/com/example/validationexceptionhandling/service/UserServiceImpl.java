package com.example.validationexceptionhandling.service;

import com.example.validationexceptionhandling.Entity.User;
import com.example.validationexceptionhandling.dto.Userdto;
import com.example.validationexceptionhandling.repositry.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositry userRepo;

    @Override
    public List<User> getAllUsersList() {
        return userRepo.findAll();
    }

    @Override
    public User save(Userdto userdto) {
        User user=User.
                build(0,userdto.getName(),userdto.getEmail(),userdto.getMobile(),
                        userdto.getGender(),userdto.getAge(),userdto.getNationality());
        return userRepo.save(user);
    }

    @Override
    public User findById(Integer id) {
        return userRepo.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        userRepo.deleteById(id);
    }
}