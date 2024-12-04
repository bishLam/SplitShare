package com.example.splitshare.activity;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitshare.databinding.ActivityRecyclerViewItemBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ActivityViewHolder extends RecyclerView.ViewHolder {
    private ActivityRecyclerViewItemBinding binding;

    public ActivityViewHolder(ActivityRecyclerViewItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void update(Activity activity) {
        String pattern = "EEE, MMM d";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(activity.getReceiptDate());
        this.binding.activityDateTextView.setText(date.toString());
        this.binding.adderTextView.setText("Added by " + activity.getSpenderFirstName() + " " + activity.getSpenderLastName());
        this.binding.amountTextView.setText(activity.getSplittedAmount().toString() + " / " + activity.getReceiptAmount().toString());
        this.binding.groupNameTextView.setText(activity.getGroupName());
        this.binding.youAreAssignedTextView.setText("You are assigned $ " + activity.getSplittedAmount());
        this.binding.statusTextView.setText(activity.getStatus());
    }
}
