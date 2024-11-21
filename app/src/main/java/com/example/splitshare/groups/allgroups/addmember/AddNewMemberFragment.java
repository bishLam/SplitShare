package com.example.splitshare.groups.allgroups.addmember;

import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splitshare.composite_tables.UserGroup;
import com.example.splitshare.databinding.AddNewMemberFragmentBinding;
import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.login.user.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.ExecutionException;

public class AddNewMemberFragment extends Fragment {

    private AddNewMemberViewModel mViewModel;
    private User user;
    private Group group;
    private AddNewMemberFragmentBinding binding;

    public static AddNewMemberFragment newInstance() {
        return new AddNewMemberFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.add_new_member_fragment, container, false);
        binding = AddNewMemberFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(AddNewMemberViewModel.class);
//        // TODO: Use the ViewModel
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(AddNewMemberViewModel.class);
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("GROUP")) {
            //at this point we know that the Group object is received successfully

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                group = bundle.getSerializable("GROUP", Group.class);
            }
            binding.groupInfoTextView.setText("Adding member for the group " + group.getGroupName());
        }





        binding.addMemberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String email = binding.memberEmailTIL.getEditText().getText().toString().trim();
                    user = mViewModel.findUserByEmail(email);

                    if(email.isEmpty()){
                        Snackbar.make(view, "Cannot be empty", Snackbar.LENGTH_LONG).show();
                    }

                    else if (user != null) {

                        if (mViewModel.findByUserAndGroupIDs(user.getUserID(), group.getGroupID()) != null) {
                            Snackbar.make(view, "User already exists", Snackbar.LENGTH_LONG).show();
                        } else {
                            UserGroup userGroup = new UserGroup(user.getUserID(), group.getGroupID(), 0.0, 0.0);
                            mViewModel.insertUserGroup(userGroup);
                            Snackbar.make(view, "User successfully added", Snackbar.LENGTH_LONG).show();
                            NavController navController = Navigation.findNavController(view);
                            navController.navigateUp();
                        }

                    } else {
                        Snackbar.make(view, "User does not exist", Snackbar.LENGTH_LONG).show();
                    }
                } catch (ExecutionException | RuntimeException | InterruptedException e) {
                    Log.e("Exception", "Exception occured");
                    Snackbar.make(view, "Exception occured", Snackbar.LENGTH_LONG).show();
                }
            }
        });


    }
}