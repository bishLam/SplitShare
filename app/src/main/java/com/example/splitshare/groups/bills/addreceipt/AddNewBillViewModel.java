package com.example.splitshare.groups.bills.addreceipt;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.splitshare.login.user.User;
import com.example.splitshare.room.SplitShareRepository;

import java.util.List;

public class AddNewBillViewModel extends AndroidViewModel {
   final private SplitShareRepository repository;
    public AddNewBillViewModel(@NonNull Application application) {
        super(application);
        repository = new SplitShareRepository(application);
    }
    // TODO: Implement the ViewModel

    public LiveData<List<User>> getAllUsers(Integer groupID){
       return repository.getUsersByGroupID(groupID);
    }


}