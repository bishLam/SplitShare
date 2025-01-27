package com.example.splitshare.groups.bills.addreceipt;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitshare.databinding.AssignBillRecyclerViewItemBinding;
import com.example.splitshare.login.user.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;

public class AssignBillViewHolder extends RecyclerView.ViewHolder {
    private final AssignBillRecyclerViewItemBinding binding;
    protected HashMap<Integer, Double> amountMap = new HashMap<>();

    public AssignBillViewHolder(@NonNull AssignBillRecyclerViewItemBinding binding, HashMap<Integer, Double> hashMap) {
        super(binding.getRoot());
        this.binding = binding;
        this.amountMap = hashMap;
    }

    public void update(User user) {
        binding.toMemberText.setText("To " + user.getFirstName() + " " + user.getLastName());
    }


}
