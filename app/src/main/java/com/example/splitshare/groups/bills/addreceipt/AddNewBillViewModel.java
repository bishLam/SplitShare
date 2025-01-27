package com.example.splitshare.groups.bills.addreceipt;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.splitshare.groups.bills.Receipt;
import com.example.splitshare.groups.splitbill.SplitBillDetails;
import com.example.splitshare.login.user.User;
import com.example.splitshare.room.SplitShareRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AddNewBillViewModel extends AndroidViewModel {
    final private SplitShareRepository repository;
    private MutableLiveData<User> users = new MutableLiveData<>();

    public AddNewBillViewModel(@NonNull Application application) {
        super(application);
        repository = new SplitShareRepository(application);
    }
    // TODO: Implement the ViewModel

    public LiveData<List<User>> getAllUsers(Integer groupID) {
        return repository.getUsersByGroupID(groupID);
    }


    public Integer getAllUsersCount(Integer groupID) {
        List<Integer> users = repository.getUsersInAGroup(groupID);
        return users.size();
    }

    public User getUserByUID(Integer UID) throws ExecutionException, InterruptedException {
        return repository.findUserByID(UID);
    }

}