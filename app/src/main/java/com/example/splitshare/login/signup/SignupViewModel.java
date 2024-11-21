package com.example.splitshare.login.signup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.splitshare.login.user.User;
import com.example.splitshare.login.user.UserDAO;
import com.example.splitshare.room.SplitShareRepository;

public class SignupViewModel extends AndroidViewModel {
    private final SplitShareRepository repository;
    public SignupViewModel(@NonNull Application application) {
        super(application);
        repository = new SplitShareRepository(application);
    }

    public Long registerUser(User user){
        return repository.insert(user);
    }

}