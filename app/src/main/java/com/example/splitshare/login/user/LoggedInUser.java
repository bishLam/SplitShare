package com.example.splitshare.login.user;

public class LoggedInUser {

    private static LoggedInUser instance;
    private User user;

    public static synchronized LoggedInUser getInstance(){
        if(instance == null){
            instance = new LoggedInUser();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void clearUser(){
        this.user = null;
    }
}


