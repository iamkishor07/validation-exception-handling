package com.example.validationexceptionhandling.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Userdto {
    @NotNull(message = "UserName shouldn't be Null or Empty")
    private  String  name;
    @Email(message = "Invalid email address")
    private  String email;
    @NotNull
    @Pattern(regexp = "^\\d{10}$",message = "Invalid mobile number entered") private  String mobile;
    private  String gender;
    @Min(16)
    @Max(90)
    private  int age;
    @NotBlank
    private  String nationality;
}
