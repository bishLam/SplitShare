package com.example.splitshare.profile;

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
import com.example.splitshare.databinding.ProfilePageFragmentBinding;
import com.example.splitshare.login.user.LoggedInUser;
import com.example.splitshare.login.user.User;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class ProfilePageFragment extends Fragment {

    private ProfilePageViewModel mViewModel;
    private ProfilePageFragmentBinding binding;

    public static ProfilePageFragment newInstance() {
        return new ProfilePageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.profile_page_fragment, container, false);
        binding = ProfilePageFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ProfilePageViewModel.class);
        super.onViewCreated(view, savedInstanceState);

        User user = LoggedInUser.getInstance().getUser();
        binding.usernameTextView.setText(user.getFirstName() + " " + user.getLastName());
        binding.userEmailTextView.setText(user.getEmail());

        binding.changeDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_profilePageFragment_to_editProfileFragment);
            }
        });

        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                builder.setTitle("Are you sure to log out?");
                builder.setMessage("You will be logged out of the application");
                builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                    NavController navController = Navigation.findNavController(view);
                    LoggedInUser.getInstance().clearUser();
                    navController.navigate(R.id.action_profilePageFragment_to_loginPageFragment);
                    Snackbar.make(view, "Logged out successfully", Snackbar.LENGTH_SHORT).show();
                    dialogInterface.cancel();
                });
                builder.setNegativeButton("No", (dialogInterface, i) -> {
                    dialogInterface.cancel();
                });
                builder.show();
            }
        });


    }
}