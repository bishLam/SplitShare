package com.example.splitshare.groups.allgroups.groupinfo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.splitshare.composite_tables.UserGroup;
import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.groups.bills.settle.owes.OwesByAndTo;
import com.example.splitshare.groups.bills.showreceipts.DisplayReceiptClass;
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

    public LiveData<List<User>> getLiveUsersByGroupID(Integer groupID) {
        return repository.getUsersByGroupID(groupID);
    }


    public Integer getTotalMembers(Integer groupID) throws ExecutionException, InterruptedException {
        return repository.getTotalMembers(groupID);
    }

    ;

    public Group findGroupByID(Integer groupID) {
        return repository.findGroupByID(groupID);
    }


    public Integer getTotalReceipts(Integer groupID) {
        return repository.getTotalReceipts(groupID);

    }

    public LiveData<List<DisplayReceiptClass>> getRecentReceipts(Integer groupID) {
        return repository.getRecentReceipts(groupID);
    }

    public List<Integer> getAllUsersInGroup(Integer groupID) {
        return repository.getUsersInAGroup(groupID);
    }

    public OwesByAndTo getOwedMoneyByUserToUser(Integer owedBy, Integer owedTo, Integer groupID) {
        return repository.getOwedMoneyByUserToUser(owedBy, owedTo, groupID);
    }

    public void leaveGroup(Integer userID, Integer groupID) {
        repository.delete(new UserGroup(userID, groupID));
    }


}