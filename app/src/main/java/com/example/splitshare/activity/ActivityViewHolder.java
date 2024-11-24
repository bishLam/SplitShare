package com.example.splitshare.activity;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitshare.databinding.ActivityRecyclerViewItemBinding;

public class ActivityViewHolder extends RecyclerView.ViewHolder {
    private ActivityRecyclerViewItemBinding binding;
    public ActivityViewHolder(@NonNull ActivityRecyclerViewItemBinding binding) {
        super(binding.getRoot());
    }

    public void update(Activity activity){
        if(activity != null) {
            this.binding.activityDateTextView.setText(activity.getReceiptDate().toString());
            this.binding.adderTextView.setText(activity.getSpenderFirstName() + " " + activity.getSpenderLastName());
            this.binding.amountTextView.setText(activity.getSplittedAmount().toString());
            this.binding.groupNameTextView.setText(activity.getGroupName());
        }
        else{
            binding.activityDateTextView.setText("");
            binding.adderTextView.setText("");
            binding.amountTextView.setText("");
            binding.groupNameTextView.setText("");
        }
    }
}
