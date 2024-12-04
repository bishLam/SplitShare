package com.example.splitshare.groups.bills.addreceipt;

import android.os.Build;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.splitshare.R;
import com.example.splitshare.databinding.AddNewBillFragmentBinding;
import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.groups.bills.Receipt;
import com.example.splitshare.groups.splitbill.SplitBillDetails;
import com.example.splitshare.login.user.LoggedInUser;
import com.example.splitshare.login.user.User;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddNewBillFragment extends Fragment {

    private AddNewBillViewModel mViewModel;
    private Group group;
    AssignBillRecyclerViewAdapter allUsersAdapter;
    Double amount = 0.00;
    Double addedamount = 0.0;
    Integer spinnerId = 0;

    public static AddNewBillFragment newInstance() {
        return new AddNewBillFragment();
    }

    private AddNewBillFragmentBinding binding;
    HashMap<Integer, Double> amountHash;

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
        if (bundle != null && bundle.containsKey("GROUP")) {
            group = (Group) bundle.getSerializable("GROUP");
            binding.assignBillRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.assignBillRecyclerView.setHasFixedSize(false);

            //create the adapter AND set it to the recycler view
            allUsersAdapter = new AssignBillRecyclerViewAdapter();
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
        binding.groupNameTextView.setText(group.getGroupName().toString().toUpperCase());


        ArrayList<String> items = new ArrayList<>();
        items.add("By Amount");
//        items.add("Split Equally");
//        items.add("By Percentage");

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

        /**
         spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0) {
        binding.enterSomethingText.setText("Enter Amount:");
        } else if (i == 1) {

        try {
        //get the amount and convert it into the double
        amount = Double.parseDouble(binding.billAmountEditText.getText().toString());
        } catch (NumberFormatException e) {
        Snackbar.make(view, "Amount can only be numbers and decimal", Snackbar.LENGTH_LONG).show();
        }
        if(amount > 0) {
        Integer usersCount = mViewModel.getAllUsersCount(group.getGroupID());
        binding.enterSomethingText.setText("Splitted Amount: " + amount/usersCount);
        binding.assignBillRecyclerView.setVisibility(View.GONE);
        }

        else{
        spinner.setSelected(false);
        Snackbar.make(view, "Please enter a valid bill amount", Snackbar.LENGTH_SHORT).show();
        }

        } else if (i == 2) {
        binding.enterSomethingText.setText("Enter Percentage");
        } else {
        binding.enterSomethingText.setText("Nothing Selected");
        }
        }

        @Override public void onNothingSelected(AdapterView<?> adapterView) {
        binding.enterSomethingText.setText("Nothing Selected");
        }
        });
         */

        binding.addBillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the values for receipt and split bill
                String description = binding.descriptionEditText.getText().toString();

                try {
                    //get the amount and convert it into the double
                    amount = Double.parseDouble(binding.billAmountEditText.getText().toString());
                } catch (NumberFormatException e) {
                    Snackbar.make(view, "Amount can only be numbers and decimal", Snackbar.LENGTH_LONG).show();
                }

                if (amount != 0 && amount > 0 && !description.isEmpty()) {
                    amountHash = allUsersAdapter.getAmountHash(); //get the hashmap from adapter
                    //iterate though the hashmap and check if total match on adding up
                    for (Map.Entry<Integer, Double> entry : amountHash.entrySet()) {
                        addedamount += entry.getValue();
                    }

                    if (addedamount.equals(amount)) {
                        Receipt receipt = new Receipt(description, amount, new Date(), LoggedInUser.getInstance().getUser().getUserID(), group.getGroupID());
                        Long receiptID = mViewModel.insert(receipt);
                        //now we need to set the split bill details
                        for (Map.Entry<Integer, Double> entry : amountHash.entrySet()) {
                            SplitBillDetails splitBillDetails = new SplitBillDetails(entry.getValue(), (int) (long) receiptID, entry.getKey(), "assigned");
                            mViewModel.insert(splitBillDetails);
                        }
                        Snackbar.make(view, "Bill Successfully added", Snackbar.LENGTH_LONG).show();
                        binding.descriptionEditText.setText("");
                        binding.billAmountEditText.setText("");
                        addedamount = 0.0;

                        NavController navController = Navigation.findNavController(view);
                        navController.navigateUp();


                    } else if (!addedamount.equals(amount)) {
                        Snackbar.make(view, "Amount do not match", Snackbar.LENGTH_LONG).show();
                        addedamount = 0.0;
                    }
                } else {
                    Snackbar.make(view, "Please enter valid amount and description", Snackbar.LENGTH_LONG).show();
                }

            }
        });

    }
}