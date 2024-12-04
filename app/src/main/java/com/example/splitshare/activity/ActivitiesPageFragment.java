package com.example.splitshare.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.splitshare.R;
import com.example.splitshare.databinding.ActivitiesPageFragmentBinding;
import com.example.splitshare.login.user.LoggedInUser;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ActivitiesPageFragment extends Fragment {

    private ActivitiesPageFragmentBinding binding;
    private ActivitiesPageViewModel mViewModel;

    public static ActivitiesPageFragment newInstance() {
        return new ActivitiesPageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.activities_page_fragment, container, false);
        binding = ActivitiesPageFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(ActivitiesPageViewModel.class);
//        // TODO: Use the ViewModel
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ActivitiesPageViewModel.class);

        //working on this. currently i am getting activities as null so it is causing the issue
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        binding.activitiesRecyclerView.setLayoutManager(layoutManager);
        binding.activitiesRecyclerView.setHasFixedSize(false);


        ActivityViewAdapter adapter = new ActivityViewAdapter();
        binding.activitiesRecyclerView.setAdapter(adapter);


        final Observer<List<Activity>> activityObserver = new Observer<List<Activity>>() {
            @Override
            public void onChanged(List<Activity> activities) {
                adapter.submitList(activities);
                if (activities.size() <= 0) {
                    binding.noActivitiesText.setVisibility(View.VISIBLE);
                    binding.activitiesRecyclerView.setVisibility(View.GONE);
                } else {
                    binding.noActivitiesText.setVisibility(View.GONE);
                    binding.activitiesRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        };


        mViewModel.getActivityByUser(LoggedInUser.getInstance().getUser().getUserID()).observe(getViewLifecycleOwner(), activityObserver);
        if (adapter.getItemCount() - 1 >= 0) {
            binding.activitiesRecyclerView.scrollToPosition(adapter.getItemCount() - 1);
        }


        binding.activeChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateRecyclerView(activityObserver, adapter);
//                binding.activitiesRecyclerView.scrollToPosition(0);
                if (adapter.getItemCount() - 1 >= 0) {
                    binding.activitiesRecyclerView.scrollToPosition(adapter.getItemCount() - 1);
                }
            }

        });

        binding.settledChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                updateRecyclerView(activityObserver, adapter);
                binding.activitiesRecyclerView.scrollToPosition(0);

                //scroll to top of the recycler view
                if (adapter.getItemCount() - 1 >= 0) {
                    binding.activitiesRecyclerView.scrollToPosition(adapter.getItemCount() - 1);
                }
            }
        });


    }

    public void updateRecyclerView(Observer<List<Activity>> activityObserver, ActivityViewAdapter adapter) {
        if (binding.activeChip.isChecked() && binding.settledChip.isChecked()) {
            mViewModel.getActivityByUser(LoggedInUser.getInstance().getUser().getUserID()).observe(getViewLifecycleOwner(), activityObserver);
        } else if (binding.activeChip.isChecked() && !binding.settledChip.isChecked()) {
            mViewModel.filterActivityActive(LoggedInUser.getInstance().getUser().getUserID()).observe(getViewLifecycleOwner(), activityObserver);
        } else if (binding.settledChip.isChecked() && !binding.activeChip.isChecked()) {
            mViewModel.filterActivitySettled(LoggedInUser.getInstance().getUser().getUserID()).observe(getViewLifecycleOwner(), activityObserver);
        } else if (!binding.settledChip.isChecked() && !binding.activeChip.isChecked()) {
            mViewModel.getActivityByUser(LoggedInUser.getInstance().getUser().getUserID()).observe(getViewLifecycleOwner(), activityObserver);
        }


    }


}