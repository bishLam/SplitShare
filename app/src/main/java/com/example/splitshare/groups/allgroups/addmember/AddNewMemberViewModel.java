package com.example.splitshare.groups.allgroups.addmember;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.splitshare.composite_tables.UserGroup;
import com.example.splitshare.login.user.User;
import com.example.splitshare.room.SplitShareRepository;

import java.util.concurrent.ExecutionException;

public class AddNewMemberViewModel extends AndroidViewModel {

    final private SplitShareRepository repository;
    public AddNewMemberViewModel(@NonNull Application application) {
        super(application);
        repository = new SplitShareRepository(application);
    }
    // TODO: Implement the ViewModel

    User findUserByEmail(String email) throws ExecutionException, InterruptedException {
        return repository.findUserByEmail(email);
    }

    UserGroup findByUserAndGroupIDs(Integer userID, Integer groupID) throws ExecutionException, InterruptedException {
        return repository.findByUserIDAndGroupID(userID, groupID);
    }

    void insertUserGroup(UserGroup userGroup){
        repository.insert(userGroup);
    }


}