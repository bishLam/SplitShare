package com.example.splitshare.groups.bills.addreceipt;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.splitshare.R;
import com.example.splitshare.databinding.AddNewBillFragmentBinding;
import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.login.user.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class AddNewBillFragment extends Fragment {

    private AddNewBillViewModel mViewModel;
    private Group group;

    public static AddNewBillFragment newInstance() {
        return new AddNewBillFragment();
    }

    private AddNewBillFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AddNewBillFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
//        return inflater.inflate(R.layout.add_new_bill_fragment, container, false);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(AddNewBillViewModel.class);
//        // TODO: Use the ViewModel
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(AddNewBillViewModel.class);
        super.onViewCreated(view, savedInstanceState);


        Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey("GROUP")){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                group = bundle.getSerializable("GROUP", Group.class);
            }
            binding.assignBillRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.assignBillRecyclerView.setHasFixedSize(true);

            //create the adapter
            AssignBillRecyclerViewAdapter allUsersAdapter = new AssignBillRecyclerViewAdapter();
            binding.assignBillRecyclerView.setAdapter(allUsersAdapter);

            //observer for livedata
            final Observer<List<User>> observer = new Observer<List<User>>() {
                @Override
                public void onChanged(List<User> users) {
                    allUsersAdapter.submitList(users);
                }
            };

            //make livedata observer for changes
            mViewModel.getAllUsers(group.getGroupID()).observe(getViewLifecycleOwner(), observer);
        }





        ArrayList<String> items = new ArrayList<>();
        items.add("By Amount");
        items.add("Split Equally");
        items.add("By Percentage");

        Spinner spinner = binding.spinner;
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.spinnerArray,
                android.R.layout.simple_spinner_dropdown_item


        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    binding.enterSomethingText.setText("Enter Amount:");
                } else if (i == 1) {
                    binding.enterSomethingText.setText("Splitted Amount:");
                    binding.enterSomethingText.setHint("Splitting equally");
                    Snackbar.make(view, "Dividing bills equally", Snackbar.LENGTH_LONG).show();
                } else if (i == 2) {
                    binding.enterSomethingText.setText("Enter Percentage");
                } else {
                    binding.enterSomethingText.setText("Nothing Selected");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                binding.enterSomethingText.setText("Nothing Selected");
            }
        });

    }
}