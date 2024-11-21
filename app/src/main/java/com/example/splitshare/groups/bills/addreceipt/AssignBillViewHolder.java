package com.example.splitshare.groups.bills.addreceipt;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitshare.databinding.AssignBillRecyclerViewItemBinding;
import com.example.splitshare.login.user.User;

public class AssignBillViewHolder extends RecyclerView.ViewHolder {
    private AssignBillRecyclerViewItemBinding binding;
    public AssignBillViewHolder(@NonNull AssignBillRecyclerViewItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void update(User user){
        binding.toMemberText.setText( "To " + user.getFirstName() + " " +user.getLastName());
    }


}
