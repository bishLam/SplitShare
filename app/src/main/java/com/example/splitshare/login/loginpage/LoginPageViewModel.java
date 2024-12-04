package com.example.splitshare.login.loginpage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.splitshare.login.loginpage.exceptions.UserNotFoundException;
import com.example.splitshare.login.loginpage.exceptions.WrongPasswordException;
import com.example.splitshare.login.user.User;
import com.example.splitshare.room.SplitShareRepository;

import java.util.concurrent.ExecutionException;

public class LoginPageViewModel extends AndroidViewModel {
    private User loggedInUser;
    private final SplitShareRepository repository;
    private String email = "";
    private String password = "";

    public LoginPageViewModel(@NonNull Application application) {
        super(application);
        repository = new SplitShareRepository(application);
    }

    public void validateUserDetails(String email, String password) throws ExecutionException, InterruptedException {

        try {
            User user = repository.findUserByEmail(email);

            if (user != null) {
                if (user.getPassword().equals(password)) {
                    loggedInUser = user;
                } else {
                    loggedInUser = null;
                    throw new WrongPasswordException("");
                }
            } else {
                throw new UserNotFoundException("No user found");
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    public User getUserbyEmail(String email) throws ExecutionException, InterruptedException {
        return repository.findUserByEmail(email);
    }

    //this is for the configuration changes


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}