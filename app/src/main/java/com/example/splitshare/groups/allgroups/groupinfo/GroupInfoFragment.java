package com.example.splitshare.groups.allgroups.groupinfo;

import androidx.annotation.MenuRes;
import androidx.appcompat.view.menu.MenuBuilder;
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

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import com.example.splitshare.R;
import com.example.splitshare.databinding.GroupInfoFragmentBinding;
import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.groups.allgroups.showgroup.detailedgroup.DetailedGroup;
import com.example.splitshare.groups.bills.settle.owes.OwesByAndTo;
import com.example.splitshare.groups.bills.showreceipts.DisplayReceiptClass;
import com.example.splitshare.groups.bills.showreceipts.ShowReceiptViewAdapter;
import com.example.splitshare.login.user.LoggedInUser;
import com.example.splitshare.login.user.User;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class GroupInfoFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

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
//        binding.optionsImageGroupInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.showContextMenu( 1f,R.menu.group_info_pop_up_menu);
//            }
//
//        });


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

            mViewModel.getLiveUsersByGroupID(group.getGroupID()).observe(getViewLifecycleOwner(), allUsersObserver);


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
                if (latestReceipt.size() == 0) {
                    binding.noReceiptsText.setVisibility(View.VISIBLE);
                    binding.allBillsButton.setVisibility(view.GONE);
                } else {
                    binding.noReceiptsText.setVisibility(View.GONE);
                    binding.allBillsButton.setVisibility(view.VISIBLE);
                }
            }

        };

        mViewModel.getRecentReceipts(group.getGroupID()).observe(getViewLifecycleOwner(), allReceiptsObserver);


        binding.optionsImageGroupInfo.setOnClickListener(v -> {
//    PopupMenu popup = new PopupMenu(this.getContext(), v);
//    popup.getMenuInflater().inflate(R.menu.group_info_pop_up_menu, popup.getMenu());
//    popup.show();
            showMenu(v);
        });


    }

    private void showMenu(View v) {

        PopupMenu popup = new PopupMenu(this.getContext(), v);
        popup.setForceShowIcon(true);
        popup.setGravity(Gravity.END);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.group_info_pop_up_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.leave_group) {
//            Snackbar.make(this.getView(), "Leave Group", Snackbar.LENGTH_LONG).show();
            //checking if the user owes someone in the group
            Integer loggedInUser = LoggedInUser.getInstance().getUser().getUserID();
            List<Integer> groupMembers = mViewModel.getAllUsersInGroup(group.getGroupID());
            boolean isOwed = false;
            boolean doesOwe = false;
            for (Integer user : groupMembers) {
                if (user != loggedInUser) {
                    OwesByAndTo owesByAndTo = mViewModel.getOwedMoneyByUserToUser(loggedInUser, user, group.getGroupID());
                    if (owesByAndTo.getAmount() != null && owesByAndTo.getAmount() > 0.0) {
                        doesOwe = true;
                    }

                    OwesByAndTo owesByAndTo2 = mViewModel.getOwedMoneyByUserToUser(user, loggedInUser, group.getGroupID());
                    if (owesByAndTo2.getAmount() != null && owesByAndTo2.getAmount() > 0.0) {
                        isOwed = true;
                    }
                }
            }

            if (doesOwe) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                builder.setTitle("Be sure to settle bills");
                builder.setMessage("Make sure to pay the bills before you leave the group. \nYou cannot leave the group until you settle all your bills");
                builder.setPositiveButton("Ok, sure", (dialogInterface, i) -> {
                    dialogInterface.cancel();
                });
                builder.show();
            } else if (isOwed) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                builder.setTitle("Be sure to settle bills");
                builder.setMessage("We found out that users in this group still owes you money. \nMake sure you get them before you leave the group");
                builder.setPositiveButton("Ok, sure", (dialogInterface, i) -> {
                    dialogInterface.cancel();
                });
                builder.setNegativeButton("Exit anyway", ((dialogInterface, i) -> {
                    dialogInterface.cancel();
                }));

                builder.show();
            } else if (!isOwed && !doesOwe) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                builder.setTitle("Are you sure you want to leave this group");
                builder.setMessage("Please note that once you leave the group, all your details in the group will be deleted. \nYou cannot join the group again unless someone adds you!");
                builder.setPositiveButton("Leave group", (dialogInterface, i) -> {
                    mViewModel.leaveGroup(loggedInUser, group.getGroupID());
                    NavController navController = Navigation.findNavController(this.getView());
                    navController.navigate(R.id.action_groupInfoFragment_to_groupFragment);
                    Snackbar.make(getView(), "Group left successfully", Snackbar.LENGTH_SHORT).show();
                    dialogInterface.cancel();
                });
                builder.setNegativeButton("Cancel", ((dialogInterface, i) -> {
                    dialogInterface.cancel();
                }));

                builder.show();
            }


            return true;
        } else if (menuItem.getItemId() == R.id.report_problem) {
            Snackbar.make(this.getView(), "Sorry this feature is not available yet. \nPlease come back later", Snackbar.LENGTH_LONG).show();
            return true;
        } else {
            return false;
        }

    }
}