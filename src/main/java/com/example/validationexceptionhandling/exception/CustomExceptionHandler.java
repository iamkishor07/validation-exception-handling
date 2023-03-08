package com.example.validationexceptionhandling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
     public Map<String,String>handleInvalidException(MethodArgumentNotValidException ex){

        Map<String,String> errormap=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errormap.put(error.getField(),error.getDefaultMessage());
        });
        return errormap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String,String>handleUserNotFoundException(UserNotFoundException ex){

        Map<String,String>errormap=new HashMap<>();
        errormap.put("Error message",ex.getLocalizedMessage());

        return errormap;
    }
}
