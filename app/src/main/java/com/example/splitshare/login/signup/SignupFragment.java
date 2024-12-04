package com.example.splitshare.login.signup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.splitshare.MainActivity;
import com.example.splitshare.R;
import com.example.splitshare.databinding.SignupFragmentBinding;
import com.example.splitshare.login.loginpage.LoginPageViewModel;
import com.example.splitshare.login.user.LoggedInUser;
import com.example.splitshare.login.user.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.ExecutionException;

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
        MainActivity activity = (MainActivity) getActivity();
        activity.hideBottomNavBar();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(requireActivity()).get(SignupViewModel.class);
        super.onViewCreated(view, savedInstanceState);
        binding.firstNameTIL.getEditText().setText(mViewModel.getFirstName());
        binding.lastNameTIL.getEditText().setText(mViewModel.getLastName());
        binding.emailTIL.getEditText().setText(mViewModel.getEmail());
        binding.passwordTIL.getEditText().setText(mViewModel.getPassword());
        binding.confirmPasswordTIL.getEditText().setText(mViewModel.getConfirmPassword());
        binding.termsAndConditionsCB.setChecked(mViewModel.getTermsAgreed());
        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = binding.firstNameTIL.getEditText().getText().toString().trim();
                String lastName = binding.lastNameTIL.getEditText().getText().toString().trim();
                String email = binding.emailTIL.getEditText().getText().toString().trim();
                String password = binding.passwordTIL.getEditText().getText().toString().trim();
                String confirmPassword = binding.confirmPasswordTIL.getEditText().getText().toString().trim();
                boolean termsAccepted = binding.termsAndConditionsCB.isChecked();

                if (!firstName.isBlank() && !lastName.isBlank() && !email.isBlank() && !password.isBlank() && !confirmPassword.isBlank() && termsAccepted) {
                    if (password.equals(confirmPassword)) {
                        try {
                            if (mViewModel.getUserByEmail(email) == null) {
                                User user = new User(firstName, lastName, email, password, "0000");
                                Long id = mViewModel.registerUser(user);
                                NavController navController = Navigation.findNavController(view);
                                User user2 = mViewModel.getUserByEmail(email);
                                LoggedInUser.getInstance().setUser(user2);
                                navController.navigate(R.id.action_signupFragment_to_homePageFragment);
                                Snackbar.make(view, "User Registered Successfully", Snackbar.LENGTH_LONG).show();
                                mViewModel.setFirstName("");
                                mViewModel.setLastName("");
                                mViewModel.setEmail("");
                                mViewModel.setPassword("");
                                mViewModel.setConfirmPassword("");
                                mViewModel.setTermsAgreed(false);
                            } else {
                                Snackbar.make(view, "User with that email already exists. Please log in instead", Snackbar.LENGTH_SHORT).show();
                            }
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }


                    } else {
                        Snackbar.make(view, "Passwords do not match", Snackbar.LENGTH_SHORT).show();
                    }
                } else {
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

        //this is for the configuration change
        binding.firstNameTIL.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mViewModel.setFirstName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.lastNameTIL.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mViewModel.setLastName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.emailTIL.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mViewModel.setEmail(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.passwordTIL.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mViewModel.setPassword(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.confirmPasswordTIL.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mViewModel.setConfirmPassword(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.termsAndConditionsCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (binding.termsAndConditionsCB.isChecked()) {
                    mViewModel.setTermsAgreed(true);
                } else {
                    mViewModel.setTermsAgreed(false);
                }
            }
        });


    }
}