package com.example.splitshare.groups.allgroups.groupinfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splitshare.R;
import com.example.splitshare.databinding.GroupInfoFragmentBinding;
import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.groups.allgroups.showgroup.detailedgroup.DetailedGroup;
import com.example.splitshare.groups.bills.showreceipts.DisplayReceiptClass;
import com.example.splitshare.groups.bills.showreceipts.ShowReceiptViewAdapter;
import com.example.splitshare.login.user.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class GroupInfoFragment extends Fragment {

    private DetailedGroup detailedGroup;
    private Group group;
    private GroupInfoViewModel mViewModel;
    private GroupInfoFragmentBinding binding;

    public static GroupInfoFragment newInstance() {
        return new GroupInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.group_info_fragment, container, false);
        binding = GroupInfoFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(GroupInfoViewModel.class);
        super.onViewCreated(view, savedInstanceState);

        //this will get the arguments passed by previous fragment to this fragment
        Bundle bundle = getArguments();

        if (bundle != null && bundle.containsKey("GROUP")) {
            //at this point we know that the Group object is received successfully
            detailedGroup = (DetailedGroup) bundle.get("GROUP");
            group = mViewModel.findGroupByID(detailedGroup.getGroupID());

            binding.groupNameTextView.setText(group.getGroupName().toString().toUpperCase());
            binding.totalReceiptsTextView.setText(mViewModel.getTotalReceipts(group.getGroupID()).toString() + " receipts");
//            binding.totalReceiptsTextView.setText(group.getTotalReceipt().toString() + " receipts");
            try {
                binding.totalMembersTextView.setText(mViewModel.getTotalMembers(group.getGroupID()).toString());
            } catch (ExecutionException e) {
                ;
            } catch (InterruptedException e) {
                ;
            }

            Observer<List<User>> allUsersObserver = new Observer<List<User>>() {
                @Override
                public void onChanged(List<User> users) {
                    binding.totalMembersTextView.setText(users.size() + " members");
                }
            };

            mViewModel.getUsersByGroupID(group.getGroupID()).observe(getViewLifecycleOwner(), allUsersObserver);


        }

        binding.addNewMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
//                navController.navigate(R.id.);

                Bundle bundle = new Bundle();
                bundle.putSerializable("GROUP", group);
                navController.navigate(R.id.action_groupInfoFragment_to_addNewMemberFragment, bundle);
            }
        });

        binding.newBillFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("GROUP", group);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_groupInfoFragment_to_addNewBillFragment, bundle);
            }
        });

        binding.allBillsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("GROUP", group);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_groupInfoFragment_to_showBillsFragment, bundle);
            }
        });

        binding.viewOwingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("GROUP", group);
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_groupInfoFragment_to_owesFragment3, bundle);
            }
        });
        binding.recentReceiptsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recentReceiptsRecyclerView.setHasFixedSize(false); // setting it on true will not allow the recycler view to be flexible with wrap content

        RecentReceiptViewAdapter adapter = new RecentReceiptViewAdapter();
        binding.recentReceiptsRecyclerView.setAdapter(adapter);

        Observer<List<DisplayReceiptClass>> allReceiptsObserver = new Observer<List<DisplayReceiptClass>>() {
            @Override
            public void onChanged(List<DisplayReceiptClass> latestReceipt) {
                adapter.submitList(latestReceipt);
            }

            };

        mViewModel.getRecentReceipts(group.getGroupID()).observe(getViewLifecycleOwner(), allReceiptsObserver);








    }
}