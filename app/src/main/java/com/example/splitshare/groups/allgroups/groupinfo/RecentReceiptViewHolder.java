package com.example.splitshare.groups.allgroups.groupinfo;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitshare.databinding.RecentReceiptRecyclerViewItemBinding;
import com.example.splitshare.groups.bills.showreceipts.DisplayReceiptClass;

public class RecentReceiptViewHolder extends RecyclerView.ViewHolder {
    private RecentReceiptRecyclerViewItemBinding binding;

    public RecentReceiptViewHolder(@NonNull RecentReceiptRecyclerViewItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void update(DisplayReceiptClass receipt) {
        binding.amountTextView.setText("$ " + receipt.getReceiptAmount());
        binding.billAdderTextView.setText(receipt.getFirstName() + " added a bill");
    }
}
