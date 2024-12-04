package com.example.splitshare.groups.bills.settle.owes;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.splitshare.databinding.OwedByAndToRecyclerViewItemBinding;

public class OwedByAndToRecyclerViewAdapter extends ListAdapter<OwesByAndTo, OwedByAndToViewHolder> {
    private OwedByAndToRecyclerViewItemBinding binding;

    public OwedByAndToRecyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<OwesByAndTo> DIFF_CALLBACK = new DiffUtil.ItemCallback<OwesByAndTo>() {
        @Override
        public boolean areItemsTheSame(@NonNull OwesByAndTo oldItem, @NonNull OwesByAndTo newItem) {
            return oldItem.getSplitID().equals(newItem.getSplitID());
        }

        @Override
        public boolean areContentsTheSame(@NonNull OwesByAndTo oldItem, @NonNull OwesByAndTo newItem) {
            return oldItem.getOwedBy().equals(newItem.getOwedBy()) &&
                    oldItem.getOwedByName().equals(newItem.getOwedByName()) &&
                    oldItem.getOwedByLastName().equals(newItem.getOwedByLastName()) &&
                    oldItem.getOwedTo().equals(newItem.getOwedTo()) &&
                    oldItem.getOwedToName().equals(newItem.getOwedToName()) &&
                    oldItem.getOwedToLastName().equals(newItem.getOwedToLastName()) &&
                    oldItem.getAmount().equals(newItem.getAmount());
        }
    };

    @NonNull
    @Override
    public OwedByAndToViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = OwedByAndToRecyclerViewItemBinding.inflate(inflater, parent, false);
        return new OwedByAndToViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OwedByAndToViewHolder holder, int position) {
        OwesByAndTo owesByAndTo = getItem(position);
        holder.update(owesByAndTo);
    }
}
