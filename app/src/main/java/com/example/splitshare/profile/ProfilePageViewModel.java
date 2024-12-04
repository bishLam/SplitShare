package com.example.splitshare.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.splitshare.room.SplitShareRepository;

public class ProfilePageViewModel extends AndroidViewModel {
    private final SplitShareRepository repository;

    public ProfilePageViewModel(@NonNull Application application) {
        super(application);
        repository = new SplitShareRepository(application);
    }
    // TODO: Implement the ViewModel
}