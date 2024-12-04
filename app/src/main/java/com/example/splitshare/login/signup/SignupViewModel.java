package com.example.splitshare.login.signup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.splitshare.login.user.User;
import com.example.splitshare.login.user.UserDAO;
import com.example.splitshare.room.SplitShareRepository;

import java.util.concurrent.ExecutionException;

public class SignupViewModel extends AndroidViewModel {
    private final SplitShareRepository repository;
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String password = "";
    private String confirmPassword = "";
    private Boolean isTermsAgreed = false;

    public SignupViewModel(@NonNull Application application) {
        super(application);
        repository = new SplitShareRepository(application);
    }

    public Long registerUser(User user) {
        return repository.insert(user);
    }

    public User getUserByEmail(String email) throws ExecutionException, InterruptedException {
        return repository.findUserByEmail(email);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Boolean getTermsAgreed() {
        return isTermsAgreed;
    }

    public void setTermsAgreed(Boolean termsAgreed) {
        isTermsAgreed = termsAgreed;
    }
}