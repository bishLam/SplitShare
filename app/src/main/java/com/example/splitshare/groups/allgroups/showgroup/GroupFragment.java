package com.example.splitshare.groups.allgroups.showgroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import com.example.splitshare.databinding.GroupFragmentBinding;
import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.groups.allgroups.showgroup.detailedgroup.DetailedGroup;
import com.example.splitshare.login.user.LoggedInUser;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class GroupFragment extends Fragment implements OnGroupClickListener{
    private GroupFragmentBinding binding;

    public static GroupFragment newInstance() {
        return new GroupFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.group_fragment, container, false);
        binding = GroupFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        GroupViewModel mViewModel = new ViewModelProvider(this).get(GroupViewModel.class);
        super.onViewCreated(view, savedInstanceState);

        binding.GroupsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.GroupsRecyclerView.setHasFixedSize(true);

        //create the adapter
        GroupsRecyclerViewAdapter adapter = new GroupsRecyclerViewAdapter(this);
        binding.GroupsRecyclerView.setAdapter(adapter);

        //get an observer and set it
        final Observer<List<DetailedGroup>> allGroupsObserver = new Observer<List<DetailedGroup>>() {
            @Override
            public void onChanged(List<DetailedGroup> groups) {
                adapter.submitList(groups);
            }
        };

        //make LiveData observe for changes

        mViewModel.getDetailedGroup(LoggedInUser.getInstance().getUser().getUserID()).observe(getViewLifecycleOwner(), allGroupsObserver);

        //redirect user to the add group page when they click on add new group FAB

        binding.newGroupFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_groupFragment_to_addNewGroupFragment);
            }
        });

        //bottom navbar implementation
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            NavController navController = Navigation.findNavController(view);
            if(item.getItemId() == R.id.homePageFragment){
                navController.navigate(R.id.action_global_homePageFragment);
                return true;
            }

            else if(item.getItemId() == R.id.groupFragment){
                navController.navigate(R.id.action_global_groupFragment);
                return true;
            }

            else if(item.getItemId() == R.id.activitiesFragment){
                navController.navigate(R.id.action_global_activitiesPageFragment);
                return true;
            }

            else if(item.getItemId() == R.id.profileFragment){
                navController.navigate(R.id.action_global_profilePageFragment);
                return true;
            }

            else{
                Snackbar.make(view, "Something wasn't right", Snackbar.LENGTH_LONG).show();
                return false;
            }

        });
    }

    @Override
    public void onGroupClick(Group group, View view) {
        //code here whenever a click happens to a particular group

    }

}