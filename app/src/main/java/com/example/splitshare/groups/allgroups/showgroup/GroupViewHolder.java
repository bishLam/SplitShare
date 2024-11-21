package com.example.splitshare.groups.allgroups.showgroup;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitshare.R;
import com.example.splitshare.databinding.GroupsRecyclerViewItemBinding;
import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.groups.allgroups.showgroup.detailedgroup.DetailedGroup;
import com.example.splitshare.room.SplitShareRepository;

import java.text.SimpleDateFormat;

public class GroupViewHolder extends RecyclerView.ViewHolder{

    private GroupsRecyclerViewItemBinding binding;

    public GroupViewHolder(@NonNull GroupsRecyclerViewItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void update(DetailedGroup group){
        this.binding.groupNameTextView.setText(group.getGroupName());
        this.binding.groupDescriptionTextView.setText(group.getGroupDescription());
        this.binding.totalMembersTextView.setText("");
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d");
        this.binding.lastReceiptTextView.setText(dateFormat.format(group.getLastReceipt()));
        this.binding.statusTextView.setText(group.getCurrentState());
        this.binding.totalMembersTextView.setText(group.getTotalMembers().toString());

        //if we are working with image we need to implement something here

    }

    //here is where we listen for clicks
    //this method will be used to
    public void bind(DetailedGroup group, OnGroupClickListener onGroupClickListener){
        binding.groupsMaterialCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                binding.groupsMaterialCardView.setChecked(!binding.groupsMaterialCardView.isChecked());
                return true;
            }
        });

        binding.groupsMaterialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create bundle and pass the group
                Bundle bundle  = new Bundle();
                bundle.putSerializable("GROUP", group);

                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_groupFragment_to_groupInfoFragment, bundle);
            }
        });

    }
}
