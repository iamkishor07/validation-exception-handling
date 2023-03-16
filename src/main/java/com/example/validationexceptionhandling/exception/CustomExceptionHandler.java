package com.example.validationexceptionhandling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {
    private String INCORRECT_REQUEST = "INCORRECT_REQUEST";
    private String BAD_REQUEST = "BAD_REQUEST";
    @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<Map<String,String>>handleInvalidException(MethodArgumentNotValidException ex){

        Map<String,String> errormap=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errormap.put(error.getField(),error.getDefaultMessage());
        });
        return new ResponseEntity<>(errormap,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex){

        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
