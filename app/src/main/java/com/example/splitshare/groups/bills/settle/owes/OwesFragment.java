package com.example.splitshare.groups.bills.settle.owes;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splitshare.R;
import com.example.splitshare.databinding.OwesFragmentBinding;
import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.login.user.LoggedInUser;
import com.example.splitshare.login.user.User;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

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
        Boolean isOwed = false;
        Boolean doesOwe = false;
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
        binding.allSettledTextView.setVisibility(view.GONE);

        List<OwesByAndTo> listToSubmit = new ArrayList<>(); //this is owed by you recycler view that is "You owe someone this amount"
        for (Integer user : users) {
            if (!userID.equals(user)) {
                OwesByAndTo owesByAndTo = mViewModel.getOwedMoneyByUserToUser(userID, user, groupID);
                if (owesByAndTo.getSplitID() != null) {
                    listToSubmit.add(owesByAndTo);
                    owedByAndToRecyclerViewAdapter.submitList(listToSubmit);
                }
            }
        }
        //to make the settled text view show or hide
        if (listToSubmit.size() > 0) {
            binding.noOweByYouTextView.setVisibility(View.GONE);
            doesOwe = true;
            binding.settleBillBtn.setVisibility(View.VISIBLE);
        } else {
            binding.noOweByYouTextView.setVisibility(View.VISIBLE);
            doesOwe = false;
            binding.settleBillBtn.setVisibility(View.GONE);
        }

        binding.moneyOwedToRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.moneyOwedToRecyclerView.setHasFixedSize(true);
        OwedByAndToRecyclerViewAdapter owedByAndToRecyclerViewAdapter2 = new OwedByAndToRecyclerViewAdapter();
        binding.moneyOwedToRecyclerView.setAdapter(owedByAndToRecyclerViewAdapter2);

        List<OwesByAndTo> listToSubmit2 = new ArrayList<>(); //this is owed to you recycler view that is "Someone owe you this amount"
        for (Integer user : users) {
            if (!userID.equals(user)) {
                OwesByAndTo owesByAndTo = mViewModel.getOwedMoneyByUserToUser(user, userID, groupID);

                if (owesByAndTo.getSplitID() != null) {
                    listToSubmit2.add(owesByAndTo);
                    owedByAndToRecyclerViewAdapter2.submitList(listToSubmit2);
                }
            }
        }
        //to make the settled text view show or hide
        if (listToSubmit2.size() > 0) {
            binding.noOwedToYouTextView.setVisibility(view.GONE);
            isOwed = true;
        } else {
            binding.noOwedToYouTextView.setVisibility(view.VISIBLE);
            isOwed = false;
        }


        if (!doesOwe && !isOwed) {
            binding.allSettledTextView.setVisibility(view.VISIBLE);
            binding.noOwedToYouTextView.setVisibility(view.GONE);
            binding.noOweByYouTextView.setVisibility(view.GONE);
            binding.settleBillBtn.setVisibility(View.GONE);
            binding.moneyOwedByRecyclerView.setVisibility(View.GONE);
            binding.moneyOwedToRecyclerView.setVisibility(View.GONE);
            binding.textView10.setVisibility(View.GONE);
            binding.textView12.setVisibility(view.GONE);

        }

        binding.settleBillBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("GROUP", group);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_owesFragment3_to_settleBillFragment, bundle);

//                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
//                builder.setTitle("Are you sure you want to settle bills");
//                builder.setMessage("Make sure to pay all the bills before hitting the \"yes\" button. \n Note: This cannot be undone");
//                builder.setPositiveButton("Yes, settle bill", (dialogInterface, i) -> {
//                    for (Integer user : users) {
//                        if (!userID.equals(user)) {
//
//                            //settle bills
//
//                        }
//                    }
//
//                });
//                builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
//                    dialogInterface.cancel();
//                });
//                builder.show();


            }
        });


    }
}