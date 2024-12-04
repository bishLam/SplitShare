
package com.example.splitshare.groups.bills.settle;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.SpinnerAdapter;

import com.example.splitshare.R;
import com.example.splitshare.databinding.SettleBillFragmentBinding;
import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.login.user.LoggedInUser;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class SettleBillFragment extends Fragment {

    private SettleBillViewModel mViewModel;
    private SettleBillFragmentBinding binding;
    private Group group;
    Integer selectedUID = 0;

    public static SettleBillFragment newInstance() {
        return new SettleBillFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = SettleBillFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //view has been created implement the method now
        mViewModel = new ViewModelProvider(this).get(SettleBillViewModel.class);
        Bundle bundle = getArguments();
        if (bundle.containsKey("GROUP")) {
            group = (Group) bundle.getSerializable("GROUP");
        }

        List<Integer> users = mViewModel.getAllUsersInAGroup(group.getGroupID());
        ArrayList<String> userNames = convertIntToStringUser(users);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, userNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.usersSpinner.setAdapter(adapter);


        binding.usersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedUID = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedUID = 0;
            }
        });


        binding.settleByGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                builder.setTitle("Are you sure you want to settle bills");
                builder.setMessage("Make sure to pay all the bills before clicking the \"yes\" button. \n Note: This cannot be undone");
                builder.setPositiveButton("Settle All", (dialogInterface, i) -> {
                    mViewModel.settleBillByUserToGroup(LoggedInUser.getInstance().getUser().getUserID(), group.getGroupID());
                    dialogInterface.cancel();
                    mViewModel.settleBillByUserToGroup(LoggedInUser.getInstance().getUser().getUserID(), group.getGroupID());
                    Snackbar.make(view, "Bills settled", Snackbar.LENGTH_LONG).show();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("GROUP", group);
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_settleBillFragment_to_owesFragment3, bundle);
                });
                builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
                    dialogInterface.cancel();
                });
                builder.show();
            }
        });

        binding.settleByUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                builder.setTitle("Are you sure you want to settle bills with " + userNames.get(selectedUID));
                builder.setMessage("Please pay user the amount and the click \"confirm\" button. \n Note: This cannot be undone");
                builder.setPositiveButton("Settle All", (dialogInterface, i) -> {
                    mViewModel.settleBillByUserToUser(LoggedInUser.getInstance().getUser().getUserID(), group.getGroupID(), users.get(selectedUID));
                    Snackbar.make(view, "Bills settled for user " + userNames.get(selectedUID), Snackbar.LENGTH_LONG).show();
                    dialogInterface.cancel();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("GROUP", group);
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_settleBillFragment_to_owesFragment3, bundle);
                });
                builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
                    dialogInterface.cancel();
                });
                builder.show();
            }
        });


    }

    public ArrayList<String> convertIntToStringUser(List<Integer> userIDs) {

        ArrayList<String> userNames = new ArrayList<>();
        for (Integer userID : userIDs) {
            String userName = mViewModel.getUserNameFromUID(userID);
            userNames.add(userName);
        }
        return userNames;
    }


}