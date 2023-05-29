package com.conferencebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LoginAlreadyTakenAdvice {

    @ResponseBody
    @ExceptionHandler(LoginAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String LoginAlreadyTakenHandler(LoginAlreadyTakenException exception){
        return exception.getMessage();
    }
}
