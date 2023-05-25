package com.conferencebackend.exception;

public class LoginAlreadyExistsException  extends RuntimeException {

    public LoginAlreadyExistsException(String login) {
        super("This login is already taken: " + login);
    }
}
