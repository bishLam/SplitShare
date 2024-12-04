package com.example.splitshare.groups.allgroups.showgroup;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.splitshare.databinding.GroupsRecyclerViewItemBinding;
import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.groups.allgroups.showgroup.detailedgroup.DetailedGroup;

public class GroupsRecyclerViewAdapter extends ListAdapter<DetailedGroup, GroupViewHolder> {

    private GroupsRecyclerViewItemBinding binding;
    private OnGroupClickListener onGroupClickListener;

    public GroupsRecyclerViewAdapter(OnGroupClickListener onGroupClickListener) {
        super(DIFF_CALLBACK);
        this.onGroupClickListener = onGroupClickListener;
    }

    private static final DiffUtil.ItemCallback<DetailedGroup> DIFF_CALLBACK = new DiffUtil.ItemCallback<DetailedGroup>() {
        @Override
        public boolean areItemsTheSame(@NonNull DetailedGroup oldItem, @NonNull DetailedGroup newItem) {
            return (oldItem.getGroupID().equals(newItem.getGroupID()));
        }

        @Override
        public boolean areContentsTheSame(@NonNull DetailedGroup oldItem, @NonNull DetailedGroup newItem) {
            return oldItem.getGroupName().equals(newItem.getGroupName()) &&
//                    oldItem.getCurrentState().equals(newItem.getCurrentState()) &&
                    oldItem.getGroupDescription().equals(newItem.getGroupDescription()) &&
//                    oldItem.getLastReceipt().equals(newItem.getLastReceipt()) &&
                    oldItem.getTotalMembers().equals(newItem.getTotalMembers());
        }
    };

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = GroupsRecyclerViewItemBinding.inflate(inflater, parent, false);
        GroupViewHolder groupViewHolder = new GroupViewHolder(binding);

        return groupViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        DetailedGroup group = getItem(position);
        holder.update(group);
        holder.bind(group, onGroupClickListener);
    }
}
