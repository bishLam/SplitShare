package com.example.splitshare.activity;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.splitshare.databinding.ActivityRecyclerViewItemBinding;

public class ActivityViewAdapter extends ListAdapter<Activity, ActivityViewHolder> {
    private ActivityRecyclerViewItemBinding binding;

    protected ActivityViewAdapter() {
        super(DIFF_CALLBACK);
    }


    private static final DiffUtil.ItemCallback<Activity> DIFF_CALLBACK = new DiffUtil.ItemCallback<Activity>() {
        @Override
        public boolean areItemsTheSame(@NonNull Activity oldItem, @NonNull Activity newItem) {
            return oldItem.getActivityID().equals(newItem.getActivityID());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Activity oldItem, @NonNull Activity newItem) {
            return oldItem.getGroupName().equals(newItem.getGroupName()) &&
                    oldItem.getReceiptAmount().equals(newItem.getReceiptAmount()) &&
                    oldItem.getReceiptDate().equals(newItem.getReceiptDate()) &&
                    oldItem.getReceiptDescription().equals(newItem.getReceiptDescription())
                    && oldItem.getSpenderFirstName().equals(newItem.getSpenderFirstName())
                    && oldItem.getSpenderLastName().equals(newItem.getSpenderLastName())
                    && oldItem.getSplittedAmount().equals(newItem.getSplittedAmount())
                    && oldItem.getStatus().equals(newItem.getStatus())
                    && oldItem.getReceiptID().equals(newItem.getReceiptID())
                    && oldItem.getGroupID().equals(newItem.getGroupID())
                    ;
        }
    };

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = ActivityRecyclerViewItemBinding.inflate(layoutInflater, parent, false);
        return new ActivityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        Activity activity = getItem(position);
        holder.update(activity);
    }
}
