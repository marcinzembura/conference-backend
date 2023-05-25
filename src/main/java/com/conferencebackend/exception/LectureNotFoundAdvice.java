package com.conferencebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LectureNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(LectureNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String LectureNotFoundHandler(LectureNotFoundException exception){
        return exception.getMessage();
    }
}
