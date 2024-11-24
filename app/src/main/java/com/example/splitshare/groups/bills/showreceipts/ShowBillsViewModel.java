package com.example.splitshare.groups.bills.showreceipts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.splitshare.groups.bills.Receipt;
import com.example.splitshare.room.SplitShareRepository;

import java.util.List;

public class ShowBillsViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private final SplitShareRepository repository;

    public ShowBillsViewModel(@NonNull Application application) {
        super(application);
        repository = new SplitShareRepository(application);
    }

    public LiveData<List<DisplayReceiptClass>> getAllReceiptsByGroupID(Integer groupID){
        return repository.getAllReceiptsByGroup(groupID);
    }

}