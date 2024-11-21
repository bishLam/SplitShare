package com.example.splitshare.login.loginpage.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super("User not Found");
    }
}
