package com.example.splitshare.groups.allgroups.addgroup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.splitshare.composite_tables.UserGroup;
import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.room.SplitShareRepository;

import java.util.concurrent.ExecutionException;

public class AddNewGroupViewModel extends AndroidViewModel {

    private final SplitShareRepository repository;

    public AddNewGroupViewModel(@NonNull Application application) {
        super(application);
        repository = new SplitShareRepository(application);
    }

    public Long addGroup(Group group) throws ExecutionException, InterruptedException {
        return repository.insert(group);
    }

    public UserGroup findGroupByGroupNameAndUID(String groupName, Integer UID) throws ExecutionException, InterruptedException {
        return repository.findGroupByNameAndUID(groupName, UID);
    }

    public Long addNewUserGroup(UserGroup userGroup) {
        return repository.insert(userGroup);
    }

}