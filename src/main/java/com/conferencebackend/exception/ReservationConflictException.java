package com.conferencebackend.exception;

public class ReservationConflictException extends RuntimeException{

    public ReservationConflictException(String time){
        super("You already have reservations for this hour "+ time);
    }
}
