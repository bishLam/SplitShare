package com.example.splitshare.login.loginpage;

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
import com.example.splitshare.databinding.LoginPageFragmentBinding;
import com.example.splitshare.login.loginpage.exceptions.UserNotFoundException;
import com.example.splitshare.login.loginpage.exceptions.WrongPasswordException;
import com.example.splitshare.login.user.LoggedInUser;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.ExecutionException;

public class LoginPageFragment extends Fragment {
    private LoginPageFragmentBinding binding;

    private LoginPageViewModel mViewModel;

    public static LoginPageFragment newInstance() {
        return new LoginPageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.login_page_fragment, container, false);
        binding = LoginPageFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(LoginPageViewModel.class);

        binding.loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = binding.emailLoginTIL.getEditText().getText().toString().trim();
                String password = binding.passwordLoginTIL.getEditText().getText().toString().trim();
                if (!userName.isBlank() && !password.isBlank()) {

                    try {
                        mViewModel.validateUserDetails(userName, password);
                        Snackbar.make(view, "Log in Successful", Snackbar.LENGTH_SHORT).show();
                        mViewModel.setLoggedInUser(mViewModel.getUserbyEmail(userName));
                        LoggedInUser.getInstance().setUser(mViewModel.getLoggedInUser());
                        NavController navController = Navigation.findNavController(view);
                        navController.navigate(R.id.action_loginPageFragment_to_homePageFragment);

                    } catch (ExecutionException e) {
                        Snackbar.make(view, "Execution Exception Occured", Snackbar.LENGTH_SHORT).show();
                    } catch (InterruptedException e) {
                        Snackbar.make(view, "Interrupted Exception  Occured", Snackbar.LENGTH_SHORT).show();
                    } catch (NullPointerException e) {
                        Snackbar.make(view, "Username/Password is incorrect", Snackbar.LENGTH_SHORT).show();
                    } catch (UserNotFoundException | WrongPasswordException e) {
                        Snackbar.make(view, "Invalid Credentials", Snackbar.LENGTH_SHORT).show();
                    }

                } else {
                    Snackbar.make(view, "Username and password cannot be empty", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        binding.SignUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_loginPageFragment_to_signupFragment);
            }
        });

    }
}