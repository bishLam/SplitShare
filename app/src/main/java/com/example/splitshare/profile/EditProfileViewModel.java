package com.example.splitshare.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.splitshare.login.user.User;
import com.example.splitshare.room.SplitShareRepository;

import java.util.concurrent.ExecutionException;

public class EditProfileViewModel extends AndroidViewModel {
    private final SplitShareRepository repository;

    public EditProfileViewModel(@NonNull Application application) {
        super(application);
        repository = new SplitShareRepository(application);
    }
    // TODO: Implement the ViewModel

    public void updateUserByUID(Integer id, String firstName, String lastName, String email, String password, String phoneNumber) {
        repository.updateUserByUID(id, firstName, lastName, email, password, phoneNumber);
    }

    public User getUserByEmail(String email) throws ExecutionException, InterruptedException {
        return repository.findUserByEmail(email);
    }


}