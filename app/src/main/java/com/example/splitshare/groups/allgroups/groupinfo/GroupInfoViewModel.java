package com.example.splitshare.groups.allgroups.groupinfo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.login.user.User;
import com.example.splitshare.room.SplitShareRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class GroupInfoViewModel extends AndroidViewModel {
    private final SplitShareRepository repository;
    private User user;
    public GroupInfoViewModel(@NonNull Application application) {
        super(application);
        repository = new SplitShareRepository(application);

    }
    // TODO: Implement the ViewModel

    public LiveData<List<User>> getUsersByGroupID(Integer groupID){
       return repository.getUsersByGroupID(groupID);
    }

    public Integer getTotalMembers(Integer groupID) throws ExecutionException, InterruptedException {
      return repository.getTotalMembers(groupID);
    };

    public Group findGroupByID(Integer groupID){
        return repository.findGroupByID(groupID);
    }





}