package com.example.splitshare.homepage;

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
import com.example.splitshare.databinding.HomePageFragmentBinding;
import com.example.splitshare.login.loginpage.LoginPageViewModel;
import com.example.splitshare.login.user.LoggedInUser;
import com.example.splitshare.login.user.User;
import com.google.android.material.snackbar.Snackbar;

public class HomePageFragment extends Fragment {

    private HomePageViewModel mViewModel;
    private HomePageFragmentBinding binding;
    private User loggedInUser;

    public static HomePageFragment newInstance() {
        return new HomePageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.home_page_fragment, container, false);
        binding = HomePageFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(HomePageViewModel.class);
//        // TODO: Use the ViewModel
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomePageViewModel.class);
        LoginPageViewModel loginPageViewModel = new ViewModelProvider(requireActivity()).get(LoginPageViewModel.class);
        loggedInUser = loginPageViewModel.getLoggedInUser();

        //log out text action
        binding.logoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                LoggedInUser.getInstance().clearUser();
                navController.navigate(R.id.action_homePageFragment_to_loginPageFragment);
            }
        });


        binding.welcomeText.setText("Welcome " + loggedInUser.getFirstName());
        binding.welcomeText2.setText("Your groups: " + "");

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

            else if(item.getItemId() == R.id.owesFragment){
                navController.navigate(R.id.action_global_activitiesPageFragment);
                return true;
            }

            else if(item.getItemId() == R.id.profileFragment){
                navController.navigate(R.id.action_global_profilePageFragment);
                return true;
            }

            else{
                Snackbar.make(view, "Something wasn't right", Snackbar.LENGTH_SHORT).show();
                return false;
            }

        });
    }
}