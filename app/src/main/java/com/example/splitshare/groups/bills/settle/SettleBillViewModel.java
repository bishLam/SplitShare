package com.example.splitshare.groups.bills.settle;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.splitshare.login.user.User;
import com.example.splitshare.room.SplitShareRepository;

import java.util.List;

public class SettleBillViewModel extends AndroidViewModel {
    private final SplitShareRepository repository;

    public SettleBillViewModel(@NonNull Application application) {
        super(application);
        repository = new SplitShareRepository(application);
    }
    // TODO: Implement the ViewModel

    public List<Integer> getAllUsersInAGroup(Integer groupID) {
        return repository.getUsersInAGroup(groupID);
    }

    public String getUserNameFromUID(Integer UID) {
        return repository.getUserNameFromUID(UID);
    }

    public void settleBillByUserToGroup(Integer userID, Integer groupID) {
        repository.settleBillByUserToGroup(userID, groupID);
    }

    public void settleBillByUserToUser(Integer settledBy, Integer groupID, Integer settledTo) {
        repository.settleBillByUserToUser(settledBy, groupID, settledTo);

    }

}