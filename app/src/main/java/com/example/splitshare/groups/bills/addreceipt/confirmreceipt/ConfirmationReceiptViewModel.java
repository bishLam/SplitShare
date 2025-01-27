package com.example.splitshare.groups.bills.addreceipt.confirmreceipt;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.splitshare.groups.bills.Receipt;
import com.example.splitshare.groups.splitbill.SplitBillDetails;
import com.example.splitshare.room.SplitShareRepository;

public class ConfirmationReceiptViewModel extends AndroidViewModel {
    private final SplitShareRepository repository;
    public ConfirmationReceiptViewModel(@NonNull Application application) {
        super(application);
        repository = new SplitShareRepository(application);
    }
    // TODO: Implement the ViewModel

    public Long insert(Receipt receipt) {
        return repository.insert(receipt);
    }


    public Long insert(SplitBillDetails splitBillDetails) {
        return repository.insert(splitBillDetails);
    }
}