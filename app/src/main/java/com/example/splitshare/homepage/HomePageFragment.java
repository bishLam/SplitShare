package com.example.splitshare.homepage;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splitshare.MainActivity;
import com.example.splitshare.R;
import com.example.splitshare.databinding.HomePageFragmentBinding;
import com.example.splitshare.login.loginpage.LoginPageViewModel;
import com.example.splitshare.login.user.LoggedInUser;
import com.example.splitshare.login.user.User;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

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
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.showBottomNavBar();

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
        binding.welcomeText.setText("Welcome " + LoggedInUser.getInstance().getUser().getFirstName());

        //log out text action
        binding.logoutText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
                builder.setTitle("Are you sure to log out?");
                builder.setMessage("You will be logged out of the application");
                builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                    NavController navController = Navigation.findNavController(view);
                    LoggedInUser.getInstance().clearUser();
                    navController.navigate(R.id.action_homePageFragment_to_loginPageFragment);
                    Snackbar.make(view, "Logged out successfully", Snackbar.LENGTH_SHORT).show();
                    dialogInterface.cancel();
                });
                builder.setNegativeButton("No", (dialogInterface, i) -> {
                    dialogInterface.cancel();
                });
                builder.show();

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.recentReceiptsRecyclerView.setLayoutManager(linearLayoutManager);
        binding.recentReceiptsRecyclerView.setHasFixedSize(false);

        HomePageBillsViewAdapter adapter = new HomePageBillsViewAdapter();
        List<DetailedReceiptClass> receipts = mViewModel.getDetailedReceiptsByUser(LoggedInUser.getInstance().getUser().getUserID());
        adapter.submitList(receipts);
        if (receipts.size() <= 0) {
            binding.noActivitiesText2.setVisibility(View.VISIBLE);
            binding.recentReceiptsRecyclerView.setVisibility(View.GONE);
        } else {
            binding.noActivitiesText2.setVisibility(View.GONE);
            binding.recentReceiptsRecyclerView.setVisibility(View.VISIBLE);
        }
        binding.recentReceiptsRecyclerView.setAdapter(adapter);


    }
}