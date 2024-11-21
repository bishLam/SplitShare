package com.example.splitshare.groups.bills.addreceipt;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.splitshare.databinding.AssignBillRecyclerViewItemBinding;
import com.example.splitshare.login.user.User;

public class AssignBillRecyclerViewAdapter extends ListAdapter<User, AssignBillViewHolder> {
    private AssignBillRecyclerViewItemBinding binding;

    protected AssignBillRecyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<User> DIFF_CALLBACK = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return (oldItem.getUserID().equals(newItem.getUserID()));
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return
                    oldItem.getFirstName().equals(newItem.getFirstName()) &&
                            oldItem.getLastName().equals(newItem.getLastName()) &&
                            oldItem.getEmail().equals(newItem.getEmail()) &&
                            oldItem.getPassword().equals(newItem.getPassword()) &&
                            oldItem.getPhoneNumber().equals(newItem.getPhoneNumber());

        }
    };

    @NonNull
    @Override
    public AssignBillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = AssignBillRecyclerViewItemBinding.inflate(inflater, parent, false);
        return new AssignBillViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignBillViewHolder holder, int position) {
        User user = getItem(position);
        holder.update(user);
    }
}
