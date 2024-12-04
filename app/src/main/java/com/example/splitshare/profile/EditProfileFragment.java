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
import com.example.splitshare.databinding.EditProfileFragmentBinding;
import com.example.splitshare.login.user.LoggedInUser;
import com.example.splitshare.login.user.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.ExecutionException;

public class EditProfileFragment extends Fragment {

    private EditProfileViewModel mViewModel;
    private EditProfileFragmentBinding binding;

    public static EditProfileFragment newInstance() {
        return new EditProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.edit_profile_fragment, container, false);
        binding = EditProfileFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EditProfileViewModel.class);

        User user = LoggedInUser.getInstance().getUser();

        binding.firstNameTIL.getEditText().setText(user.getFirstName());
        binding.lastNameTIL.getEditText().setText(user.getLastName());
        binding.emailTIL.getEditText().setText(user.getEmail());
        binding.passwordTIL.getEditText().setText(user.getPassword());
        binding.phoneNumberTIL.getEditText().setText(user.getPhoneNumber());

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigateUp();
            }
        });

        binding.saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = binding.firstNameTIL.getEditText().getText().toString();
                String lastName = binding.lastNameTIL.getEditText().getText().toString();
                String email = binding.emailTIL.getEditText().getText().toString();
                String password = binding.passwordTIL.getEditText().getText().toString();
                String phoneNumber = binding.phoneNumberTIL.getEditText().getText().toString();

                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
                    Snackbar.make(view, "Please fill in all fields", Snackbar.LENGTH_LONG).show();
                } else {
                    User loggedinUser = LoggedInUser.getInstance().getUser();
                    if (loggedinUser.getFirstName().equals(firstName) && loggedinUser.getLastName().equals(lastName) && loggedinUser.getEmail().equals(email) && loggedinUser.getPassword().equals(password) && loggedinUser.getPhoneNumber().equals(phoneNumber)) {
                        Snackbar.make(view, "No changes were made", Snackbar.LENGTH_LONG).show();
                    } else {
                        mViewModel.updateUserByUID(loggedinUser.getUserID(), firstName, lastName, email, password, phoneNumber);
                        try {
                            LoggedInUser.getInstance().setUser(mViewModel.getUserByEmail(email));
                        } catch (ExecutionException e) {
                            Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_SHORT).show();
                        } catch (InterruptedException e) {
                            Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_SHORT).show();
                        }
                        NavController navController = Navigation.findNavController(view);
                        Snackbar.make(view, "Profile updated successfully", Snackbar.LENGTH_SHORT).show();
                        navController.navigateUp();
                    }
                }

            }
        });

    }
}