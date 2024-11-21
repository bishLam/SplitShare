package com.example.splitshare.groups.allgroups.addgroup;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splitshare.composite_tables.UserGroup;
import com.example.splitshare.databinding.AddNewGroupFragmentBinding;
import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.login.loginpage.LoginPageViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public class AddNewGroupFragment extends Fragment {

    private AddNewGroupViewModel mViewModel;
    private LoginPageViewModel loginPageViewModel;
    private AddNewGroupFragmentBinding binding;


    public static AddNewGroupFragment newInstance() {
        return new AddNewGroupFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.add_new_group_fragment, container, false);
    binding = AddNewGroupFragmentBinding.inflate(inflater, container, false);
    return binding.getRoot();
    }


//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(AddNewGroupViewModel.class);
//        // TODO: Use the ViewModel
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(AddNewGroupViewModel.class);
        loginPageViewModel = new ViewModelProvider(requireActivity()).get(LoginPageViewModel.class);
        Integer UID = loginPageViewModel.getLoggedInUser().getUserID();

        super.onViewCreated(view, savedInstanceState);



        binding.addGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String groupName = binding.groupNameTIL.getEditText().getText().toString().trim();
                String groupDescription = binding.groupDescriptionTIL.getEditText().getText().toString().trim();
                if(!groupName.isEmpty() && !groupDescription.isEmpty()){
                    try {
                        UserGroup userGroup = mViewModel.findGroupByGroupNameAndUID(groupName, UID);
                        if(userGroup == null){
                            Group group = new Group(groupName, groupDescription, 0, new Date(), "Active");
                            Integer groupID = Math.toIntExact(mViewModel.addGroup(group));
                            UserGroup userGroup1 = new UserGroup(UID, groupID, 0.0, 0.0);
                            mViewModel.addNewUserGroup(userGroup1);
                            Snackbar.make(view, "Group Added Successfully", Snackbar.LENGTH_LONG).show();
                            NavController navController = Navigation.findNavController(view);
                            navController.navigateUp();
                        }
                        else{
                            Snackbar.make(view, "Group already exists", Snackbar.LENGTH_LONG).show();



                        }
                    } catch (ExecutionException | InterruptedException e) {
                        Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_LONG).show();
                    }
                }

                else{
                    Snackbar.make(view, "Please fill all the fields", Snackbar.LENGTH_LONG).show();
                }
            }
        });






    }
}