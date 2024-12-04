package com.example.splitshare.groups.allgroups.groupinfo;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.splitshare.databinding.RecentReceiptRecyclerViewItemBinding;
import com.example.splitshare.groups.bills.showreceipts.DisplayReceiptClass;

public class RecentReceiptViewAdapter extends ListAdapter<DisplayReceiptClass, RecentReceiptViewHolder> {
    private RecentReceiptRecyclerViewItemBinding binding;

    protected RecentReceiptViewAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<DisplayReceiptClass> DIFF_CALLBACK = new DiffUtil.ItemCallback<DisplayReceiptClass>() {
        @Override
        public boolean areItemsTheSame(@NonNull DisplayReceiptClass oldItem, @NonNull DisplayReceiptClass newItem) {
            return
                    oldItem.getReceiptID().equals(newItem.getReceiptID());
        }

        @Override
        public boolean areContentsTheSame(@NonNull DisplayReceiptClass oldItem, @NonNull DisplayReceiptClass newItem) {
            return
                    oldItem.getUserID().equals(newItem.getUserID()) &&
                            oldItem.getGroupID().equals(newItem.getGroupID()) &&
                            oldItem.getReceiptAmount().equals(newItem.getReceiptAmount()) &&
                            oldItem.getReceiptDate().equals(newItem.getReceiptDate()) &&
                            oldItem.getReceiptDescription().equals(newItem.getReceiptDescription())
                    ;
        }
    };

    @NonNull
    @Override
    public RecentReceiptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = RecentReceiptRecyclerViewItemBinding.inflate(layoutInflater, parent, false);
        return new RecentReceiptViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentReceiptViewHolder holder, int position) {
        DisplayReceiptClass displayReceiptClass = getItem(position);
        holder.update(displayReceiptClass);
    }
}
