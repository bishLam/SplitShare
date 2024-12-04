package com.example.splitshare.homepage;

import static java.lang.System.in;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.room.SplitShareRepository;

import java.util.List;

public class HomePageViewModel extends AndroidViewModel {
    private final SplitShareRepository repository;

    public HomePageViewModel(@NonNull Application application) {
        super(application);
        repository = new SplitShareRepository(application);
    }

    public LiveData<List<Group>> getTotalGroupsByUID(Integer uid) {
        LiveData<List<Group>> groups = repository.getGroupsByUserID(uid);
        return groups;
    }

    public List<DetailedReceiptClass> getDetailedReceiptsByUser(Integer userID) {
        return repository.getDetailedReceiptsByUser(userID);
    }

    ;


}