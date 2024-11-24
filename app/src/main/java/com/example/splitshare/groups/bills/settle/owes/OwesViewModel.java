package com.example.splitshare.groups.bills.settle.owes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.splitshare.login.user.User;
import com.example.splitshare.room.SplitShareRepository;

import java.util.List;

public class OwesViewModel extends AndroidViewModel {
    private final SplitShareRepository repository;
    public OwesViewModel(@NonNull Application application) {
        super(application);
        repository = new SplitShareRepository(application);
    }
    // TODO: Implement the ViewModel

    public OwesByAndTo getOwedMoneyByUserToUser(Integer owedBy, Integer owedTo, Integer groupID){
        return repository.getOwedMoneyByUserToUser(owedBy, owedTo, groupID);
    }

    public List<Integer> getAllUsersInGroup(Integer groupID){
        return repository.getUsersInAGroup(groupID);
    }
}