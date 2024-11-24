package com.example.splitshare.groups.bills.settle.owes;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splitshare.R;
import com.example.splitshare.databinding.OwesFragmentBinding;
import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.login.user.LoggedInUser;
import com.example.splitshare.login.user.User;

import java.util.ArrayList;
import java.util.List;

public class OwesFragment extends Fragment {

    private OwesViewModel mViewModel;
    private OwesFragmentBinding binding;
    private Group group;
    List<Integer> users;

    public static OwesFragment newInstance() {
        return new OwesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.owes_fragment, container, false);
        binding = OwesFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(OwesViewModel.class);
//        // TODO: Use the ViewModel
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(OwesViewModel.class);
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("GROUP")) {
            group = (Group) bundle.getSerializable("GROUP");
            binding.groupNameTextView.setText(group.getGroupName().toString().toUpperCase());
        }

        binding.moneyOwedByRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.moneyOwedByRecyclerView.setHasFixedSize(true);

        OwedByAndToRecyclerViewAdapter owedByAndToRecyclerViewAdapter = new OwedByAndToRecyclerViewAdapter();
        binding.moneyOwedByRecyclerView.setAdapter(owedByAndToRecyclerViewAdapter);

        Integer userID = LoggedInUser.getInstance().getUser().getUserID();
        Integer groupID = group.getGroupID();
        users = mViewModel.getAllUsersInGroup(groupID);
        binding.totalMembersTextView.setText(users.size() + " Members");

        List<OwesByAndTo> listToSubmit = new ArrayList<>();
        for (Integer user : users) {
            if (!userID.equals(user)) {
                OwesByAndTo owesByAndTo = mViewModel.getOwedMoneyByUserToUser(userID, user, groupID);
                listToSubmit.add(owesByAndTo);
                owedByAndToRecyclerViewAdapter.submitList(listToSubmit);
            }
        }

        binding.moneyOwedToRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.moneyOwedToRecyclerView.setHasFixedSize(true);
        OwedByAndToRecyclerViewAdapter owedByAndToRecyclerViewAdapter2 = new OwedByAndToRecyclerViewAdapter();
        binding.moneyOwedToRecyclerView.setAdapter(owedByAndToRecyclerViewAdapter2);

        List<OwesByAndTo> listToSubmit2 = new ArrayList<>();
        for (Integer user : users) {
            if (!userID.equals(user)) {
                OwesByAndTo owesByAndTo = mViewModel.getOwedMoneyByUserToUser(user, userID, groupID);
                    listToSubmit2.add(owesByAndTo);
                    owedByAndToRecyclerViewAdapter2.submitList(listToSubmit2);
            }
        }


    }
}