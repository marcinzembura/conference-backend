package com.conferencebackend.exception;

public class LectureIsFullException extends RuntimeException{

    public LectureIsFullException(Long id){
        super("This lecture is full: "+ id);
    }
}
