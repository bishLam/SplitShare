package com.example.splitshare.groups.allgroups.showgroup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.groups.allgroups.showgroup.detailedgroup.DetailedGroup;
import com.example.splitshare.login.user.LoggedInUser;
import com.example.splitshare.room.SplitShareRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class GroupViewModel extends AndroidViewModel {
    private final SplitShareRepository repository;
    private LiveData<List<Group>> getAllGroupsByUser;

    public LiveData<List<Group>> getAllGroupsByUser() {
        return getAllGroupsByUser;
    }

    public GroupViewModel(@NonNull Application application) {
        super(application);
        repository = new SplitShareRepository(application);
        getAllGroupsByUser = repository.getGroupsByUserID(LoggedInUser.getInstance().getUser().getUserID());
    }
    // TODO: Implement the ViewModel

    public Integer getTotalMembers(Integer groupID) throws ExecutionException, InterruptedException {
        return repository.getTotalMembers(groupID);
    };

    public LiveData<List<DetailedGroup>> getDetailedGroup(Integer userID) {
        return repository.getDetailedGroup(userID);
    }


}