package com.example.splitshare.groups.bills.addreceipt;

import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitshare.databinding.SelectMemberRecyclerViewItemBinding;
import com.example.splitshare.login.user.User;
import com.google.android.material.snackbar.Snackbar;

public class SelectUserViewHolder extends RecyclerView.ViewHolder {
    private final SelectMemberRecyclerViewItemBinding binding;

    public SelectUserViewHolder(@NonNull SelectMemberRecyclerViewItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void update(User user) {
        binding.userNameCheckBox.setText("To " + user.getFirstName() + " " + user.getLastName());
        binding.userNameCheckBox.setChecked(true);
    }
}
