package com.conferencebackend.exception;

public class LectureNotFoundException extends RuntimeException{

    public LectureNotFoundException(Long id){
        super("Could not find lecture: "+ id);
    }
}
