package com.example.splitshare.groups.bills.addreceipt;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.splitshare.databinding.SelectMemberRecyclerViewItemBinding;
import com.example.splitshare.login.user.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.List;

public class SelectUserViewAdapter extends ListAdapter<User, SelectUserViewHolder> {
    private SelectMemberRecyclerViewItemBinding binding;
    private HashMap<User, Boolean> allUsers;


    public SelectUserViewAdapter(HashMap<User, Boolean> allUsers) {
        super(DIFF_CALLBACK);
        this.allUsers = allUsers;
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
    public SelectUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = SelectMemberRecyclerViewItemBinding.inflate(layoutInflater, parent, false);
        return new SelectUserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectUserViewHolder holder, int position) {
        User user = getItem(position);
        binding.userNameCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                Snackbar.make(binding.getRoot(), "Check changed", Snackbar.LENGTH_LONG).show();

                if (allUsers != null) {
                    if (b) {
                        allUsers.put(user, true);
                    }
                    if(!b){
                        allUsers.put(user, false);
                    }
                }
            }

        });
        holder.update(user);
    }

    public HashMap<User, Boolean> getAllUsers() {
        return allUsers;
    }
}
