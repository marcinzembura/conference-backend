package com.conferencebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LectureIsFullAdvice {
    @ResponseBody
    @ExceptionHandler(LectureIsFullException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String LectureNotFoundHandler(LectureIsFullException exception){
        return exception.getMessage();
    }
}
