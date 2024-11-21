package com.example.splitshare.login.signup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splitshare.R;
import com.example.splitshare.databinding.SignupFragmentBinding;
import com.example.splitshare.login.loginpage.LoginPageViewModel;
import com.example.splitshare.login.user.User;
import com.google.android.material.snackbar.Snackbar;

public class SignupFragment extends Fragment {

    private SignupFragmentBinding binding;
    private SignupViewModel mViewModel;

    public static SignupFragment newInstance() {
        return new SignupFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.signup_fragment, container, false);
        binding = SignupFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SignupViewModel.class);
        super.onViewCreated(view, savedInstanceState);
        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = binding.firstNameTIL.getEditText().getText().toString().trim();
                String lastName = binding.lastNameTIL.getEditText().getText().toString().trim();
                String email = binding.emailTIL.getEditText().getText().toString().trim();
                String password = binding.passwordTIL.getEditText().getText().toString().trim();
                String confirmPassword = binding.confirmPasswordTIL.getEditText().getText().toString().trim();
                boolean termsAccepted = binding.termsAndConditionsCB.isChecked();

                if(!firstName.isBlank() && !lastName.isBlank() && !email.isBlank() && !password.isBlank() && !confirmPassword.isBlank() && termsAccepted){
                    if(password.equals(confirmPassword)){
                        User user = new User(firstName, lastName, email, password, "0000");
                        Long id =mViewModel.registerUser(user);
                        NavController navController = Navigation.findNavController(view);
                        navController.navigate(R.id.action_signupFragment_to_homePageFragment);
                        Snackbar.make(view, "User Registered Successfully", Snackbar.LENGTH_LONG).show();
                        LoginPageViewModel loginPageViewModel = new ViewModelProvider(requireActivity()).get(LoginPageViewModel.class);
                        loginPageViewModel.setLoggedInUser(user);
                    }

                    else{
                        Snackbar.make(view, "Passwords do not match", Snackbar.LENGTH_SHORT).show();
                    }
                }

                else{
                    Snackbar.make(view, "All the fields with * is required", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        binding.loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_signupFragment_to_loginPageFragment);
            }
        });


    }
}