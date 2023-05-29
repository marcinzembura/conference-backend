package com.conferencebackend.exception;

public class LoginAlreadyTakenException extends RuntimeException{

    public LoginAlreadyTakenException(String login){
        super("This login is taken: "+ login);
    }
}
