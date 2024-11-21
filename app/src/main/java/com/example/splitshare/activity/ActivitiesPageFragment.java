package com.example.splitshare.activity;

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

import com.example.splitshare.R;
import com.example.splitshare.databinding.ActivitiesPageFragmentBinding;
import com.google.android.material.snackbar.Snackbar;

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
        return  binding.getRoot();
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

        //implemement the bottom nav
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
}