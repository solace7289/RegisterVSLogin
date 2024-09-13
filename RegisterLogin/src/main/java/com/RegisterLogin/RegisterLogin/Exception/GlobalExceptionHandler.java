package com.RegisterLogin.RegisterLogin.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //hander runtime exception
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<String> handingRuntimeException(RuntimeException exception){

        //error base on user fault -> badrequest = 400
        // return a responseentity
        return ResponseEntity.badRequest().body(exception.getMessage());
    }



}
