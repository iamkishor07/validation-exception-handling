package com.example.validationexceptionhandling.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Entity
@Table(name = "Users_Data")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int userId;
    @Column(name = "username") private  String  name;
    @Column(name = "email") private  String email;
    @Column(name = "mobile") private  String mobile;
    @Column(name = "gender") private  String gender;
    @Column(name = "age") private  int age;
    @Column(name = "nationality") private  String nationality;


}
