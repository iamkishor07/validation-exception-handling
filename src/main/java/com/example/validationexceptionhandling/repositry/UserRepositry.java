package com.example.validationexceptionhandling.repositry;

import com.example.validationexceptionhandling.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositry extends JpaRepository<User,Integer> {
    User findByUserId(int id);

}
