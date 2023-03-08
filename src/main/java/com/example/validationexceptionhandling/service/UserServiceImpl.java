package com.example.validationexceptionhandling.service;

import com.example.validationexceptionhandling.Entity.User;
import com.example.validationexceptionhandling.dto.Userdto;
import com.example.validationexceptionhandling.exception.UserNotFoundException;
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


    public User save(Userdto userdto)  {
        User user=User.
                build(0,userdto.getName(),userdto.getEmail(),userdto.getMobile(),
                        userdto.getGender(),userdto.getAge(),userdto.getNationality());
        return userRepo.save(user);
    }

    @Override
    public User findById(Integer id) throws UserNotFoundException  {
        User user= userRepo.findByUserId(id);
        if(user!=null){
            return user;
        }else{
            throw new UserNotFoundException("User not find with the given Id "+id);
        }
    }

    @Override
    public void delete(Integer id) throws UserNotFoundException {
        if(userRepo.findByUserId(id)!=null){
            userRepo.deleteById(id);
        }else{
            throw new UserNotFoundException("Can't delete,No records found with the given ID: "+id);
        }

    }

    @Override
    public User update(Userdto userdto, Integer id) throws UserNotFoundException {
        User user= userRepo.findByUserId(id);
        if(user!=null){
            User user1=userRepo.getById(id);
            user1.setName(userdto.getName());
            user1.setAge(userdto.getAge());
            user1.setEmail(userdto.getEmail());
            user1.setGender(userdto.getGender());
            user1.setMobile(userdto.getMobile());
            user1.setNationality(userdto.getNationality());
            return userRepo.save(user1);
        }else{
            throw new UserNotFoundException("Can't update,No records found with the given Id: "+id);
        }

    }
}