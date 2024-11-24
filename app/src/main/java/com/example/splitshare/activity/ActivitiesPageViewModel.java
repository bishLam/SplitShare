package com.example.splitshare.activity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.splitshare.room.SplitShareRepository;

import java.util.List;

public class ActivitiesPageViewModel extends AndroidViewModel {
    private final SplitShareRepository repository;
    public ActivitiesPageViewModel(@NonNull Application application) {
        super(application);
        repository = new SplitShareRepository(application);
    }
    // TODO: Implement the ViewModel

    public LiveData<List<Activity>> getActivityByUser(Integer id){
        return repository.getActivityByUser(id);
    }
}