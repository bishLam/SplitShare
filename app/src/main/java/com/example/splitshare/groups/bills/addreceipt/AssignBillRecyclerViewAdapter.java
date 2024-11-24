package com.example.splitshare.groups.bills.addreceipt;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.splitshare.databinding.AssignBillRecyclerViewItemBinding;
import com.example.splitshare.login.user.User;

import java.util.HashMap;

public class AssignBillRecyclerViewAdapter extends ListAdapter<User, AssignBillViewHolder> {
    private AssignBillRecyclerViewItemBinding binding;
    public final HashMap<Integer, Double> amountHash = new HashMap<>();

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
        return new AssignBillViewHolder(binding, amountHash);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignBillViewHolder holder, int position) {
        User user = getItem(position);
        holder.update(user);
    }

    //this is so that we can store the logic in the fragment from the values and keys
    public HashMap<Integer, Double> getAmountHash(){
        return amountHash;
    }
}
