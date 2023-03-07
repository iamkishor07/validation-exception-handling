package com.example.validationexceptionhandling.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Userdto {
    private  String  name;
    private  String email;
    private  String mobile;
    private  String gender;
    private  int age;
    private  String nationality;
}
