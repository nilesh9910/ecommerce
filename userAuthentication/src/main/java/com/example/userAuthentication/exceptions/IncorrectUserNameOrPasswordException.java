package com.example.userAuthentication.exceptions;

public class IncorrectUserNameOrPasswordException extends Exception{
    public IncorrectUserNameOrPasswordException(String message) {
        super(message);
    }
}
