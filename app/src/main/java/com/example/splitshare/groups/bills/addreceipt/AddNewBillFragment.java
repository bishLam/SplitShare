package com.example.splitshare.groups.bills.addreceipt;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.example.splitshare.R;
import com.example.splitshare.databinding.AddNewBillFragmentBinding;
import com.example.splitshare.groups.allgroups.Group;
import com.example.splitshare.groups.bills.addreceipt.confirmreceipt.ConfirmReceiptDetailedReceiptClass;
import com.example.splitshare.login.user.User;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class AddNewBillFragment extends Fragment {

    private AddNewBillViewModel mViewModel;
    private Group group;
    Double amount = 0.00;
    Double addedamount = 0.0;
    String splitType = "By Amount";
    private AssignBillRecyclerViewAdapter assignBillRecyclerViewAdapter;
    private SelectUserViewAdapter usersCheckboxAdapter;
    HashMap<User, Boolean> allUsers = new HashMap<>();
    Date selectedDate = null;

    public static AddNewBillFragment newInstance() {
        return new AddNewBillFragment();
    }

    private AddNewBillFragmentBinding binding;
    private HashMap<Integer, Double> amountHash;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setSplitType("By Amount");
        binding = AddNewBillFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(AddNewBillViewModel.class);
        super.onViewCreated(view, savedInstanceState);


        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("GROUP")) {
            group = (Group) bundle.getSerializable("GROUP");
        }

        if (group != null) {
            //this is the default view of the fragment
            binding.assignBillRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.assignBillRecyclerView.setHasFixedSize(false);

            //create the adapter AND set it to the recycler view for the assign amount section
            assignBillRecyclerViewAdapter = new AssignBillRecyclerViewAdapter(getSplitType());
            binding.assignBillRecyclerView.setAdapter(assignBillRecyclerViewAdapter);

            //observer for livedata
            final Observer<List<User>> observer = new Observer<List<User>>() {
                @Override
                public void onChanged(List<User> users) {
                    assignBillRecyclerViewAdapter.submitList(users);
                }
            };

            //make livedata observer for changes
            mViewModel.getAllUsers(group.getGroupID()).observe(getViewLifecycleOwner(), observer);
            binding.groupNameTextView.setText(group.getGroupName().toString().toUpperCase());
        }

        //date functionality
        {
            //constrains to ensure invalid date is not selected
            CalendarConstraints.Builder constraints = new CalendarConstraints.Builder();
            constraints.setValidator(DateValidatorPointBackward.now());
            MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select a date").setCalendarConstraints(constraints.build()).build();

            binding.dateIconImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    datePicker.show(getParentFragmentManager(), "DATE_PICKER");
                }
            });

            binding.selectedDateTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    datePicker.show(getParentFragmentManager(), "DATE_PICKER");
                }
            });

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String date = simpleDateFormat.format(new Date());
            binding.selectedDateTextView.setText(date);

            datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                @Override
                public void onPositiveButtonClick(Object selection) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String date = simpleDateFormat.format(datePicker.getSelection());
                    binding.selectedDateTextView.setText(date);

                    selectedDate = new Date((long) selection);

                }
            });
        }


        //making the checkboxes recycler view inflated with all the users
        binding.selectPeopleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.selectPeopleRecyclerView.setHasFixedSize(false);

        allUsers.clear(); //this is to make sure when the user go into the next page and comes back, the duplication of users is not done
        final Observer<List<User>> usersObserver = new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User user : users) {
                    allUsers.put(user, true);
                }
                usersCheckboxAdapter.submitList(users);
            }
        };

        mViewModel.getAllUsers(group.getGroupID()).observe(getViewLifecycleOwner(), usersObserver);
        binding.addBillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the values for receipt and split bill
                String description = binding.descriptionEditText.getText().toString();
                if(selectedDate == null){
                    selectedDate = new Date();
                }
                try {
                    //get the amount and convert it into the double
                    amount = Double.parseDouble(binding.billAmountEditText.getText().toString());
                } catch (NumberFormatException e) {
                    Snackbar.make(view, "Amount can only be numbers and decimal", Snackbar.LENGTH_LONG).show();
                }
                HashMap<User, Double> userAmountHash = new HashMap<>(); //this hashmap is to transfer this data into the confirm receipt fragment
                if(Objects.equals(getSplitType(), "By Amount") || Objects.equals(getSplitType(), "Equally")){
                    if (amount != 0 && amount > 0 && !description.isEmpty()) {
                        amountHash = assignBillRecyclerViewAdapter.getAmountHash(); //get the hashmap from adapter
                        //iterate though the hashmap and check if total match on adding up
                        for (Map.Entry<Integer, Double> entry : amountHash.entrySet()) {
                            addedamount += entry.getValue();
                            try {
                                userAmountHash.put(mViewModel.getUserByUID(entry.getKey()), entry.getValue());
                            } catch (ExecutionException | InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        if (addedamount.equals(amount)) {
                            ConfirmReceiptDetailedReceiptClass confirmReceiptDetailedReceiptClass = new ConfirmReceiptDetailedReceiptClass();
                            confirmReceiptDetailedReceiptClass.setReceiptAmount(amount);
                            confirmReceiptDetailedReceiptClass.setReceiptDescription(description);
                            confirmReceiptDetailedReceiptClass.setAmountSplit(userAmountHash);
                            confirmReceiptDetailedReceiptClass.setReceiptDate(selectedDate);
                            confirmReceiptDetailedReceiptClass.setGroup(group);

//
//                        binding.descriptionEditText.setText("");
//                        binding.billAmountEditText.setText("");
//                        selectedDate = null;
//                        addedamount = 0.0;

                            Bundle bundle = new Bundle();
                            bundle.putSerializable("RECEIPT", confirmReceiptDetailedReceiptClass);


                            NavController navController = Navigation.findNavController(view);
                            navController.navigate(R.id.action_addNewBillFragment_to_confirmationReceiptFragment, bundle);


                        } else if (!addedamount.equals(amount)) {
                            Snackbar.make(view, "Amount do not match", Snackbar.LENGTH_LONG).show();
                            addedamount = 0.0;
                        }
                    } else {
                        Snackbar.make(view, "Please enter valid amount and description", Snackbar.LENGTH_LONG).show();
                    }
                }

                else if(getSplitType() == "By Percentage"){
                    if (amount != 0 && amount > 0 && !description.isEmpty()) {
                        //this hashmap is used to check if the total divided percentage is 100
                        HashMap<Integer, Double> percentageHashmap = new HashMap<>();
                        percentageHashmap = assignBillRecyclerViewAdapter.getPercentageHash();
                        Double percentageTotal = 0.0;
                        Double amountTotal = 0.0;
                        for (Map.Entry<Integer, Double> entry: percentageHashmap.entrySet()) {
                            percentageTotal += entry.getValue();
                            Double amountPassed = entry.getValue() * amount/100;
                            try {
                                userAmountHash.put(mViewModel.getUserByUID(entry.getKey()), amountPassed);
                                amountTotal += amountPassed;
                            } catch (ExecutionException | InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if(percentageTotal.equals(100.0) && amountTotal.equals(amount)){
//                            Snackbar.make(view, "This feature is not available yet", Snackbar.LENGTH_LONG).show();
                            ConfirmReceiptDetailedReceiptClass confirmReceiptDetailedReceiptClass = new ConfirmReceiptDetailedReceiptClass();
                            confirmReceiptDetailedReceiptClass.setReceiptAmount(amount);
                            confirmReceiptDetailedReceiptClass.setReceiptDescription(description);
                            confirmReceiptDetailedReceiptClass.setAmountSplit(userAmountHash);
                            confirmReceiptDetailedReceiptClass.setReceiptDate(selectedDate);
                            confirmReceiptDetailedReceiptClass.setGroup(group);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("RECEIPT", confirmReceiptDetailedReceiptClass);

                            NavController navController = Navigation.findNavController(view);
                            navController.navigate(R.id.action_addNewBillFragment_to_confirmationReceiptFragment, bundle);

                        }

                        else{
                            Snackbar.make(view, "Percentage should add up to 100", Snackbar.LENGTH_LONG).show();
                        }
                        
                    }

                    else{
                        Snackbar.make(view, "Please enter valid amount and description", Snackbar.LENGTH_LONG).show();
                    }
                }



            }
        });

        //create the adapter AND set it to the recycler view
        usersCheckboxAdapter = new SelectUserViewAdapter(allUsers); //allUsers
        binding.selectPeopleRecyclerView.setAdapter(usersCheckboxAdapter);
        //Tab layout to make the tab selection
        {
            binding.tabLayout.setElevation(1);
            TabLayout.Tab tab = binding.tabLayout.getTabAt(1);
            tab.select();
            binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if (tab.getPosition() == 0) {
                        usersCheckboxAdapter.submitList(filterAllUsers(allUsers));
                        setSplitType("Equally");
                        Snackbar.make(view, "Splitting equally", Snackbar.LENGTH_LONG).show();
                        binding.enterSomethingText.setText("Splittled Amount");
                        if (group != null) {
                            //create the adapter AND set it to the recycler view with selected users
                            assignBillRecyclerViewAdapter = new AssignBillRecyclerViewAdapter(getSplitType());
                            binding.assignBillRecyclerView.setAdapter(assignBillRecyclerViewAdapter);
                            HashMap<User, Boolean>  selectedUsers = usersCheckboxAdapter.getAllUsers();
                            assignBillRecyclerViewAdapter.submitList(filterSelectedUsers(selectedUsers));

                            String amountStr = binding.billAmountEditText.getText().toString();

                            try {
                                Double amount = Double.parseDouble(amountStr);
                                Integer usersCount = getSelectedUsersCount(allUsers);
                                if(usersCount > 0) {
                                    amount = amount / usersCount;
                                    assignBillRecyclerViewAdapter = new AssignBillRecyclerViewAdapter(getSplitType(), amount);
                                    binding.assignBillRecyclerView.setAdapter(assignBillRecyclerViewAdapter);
                                    assignBillRecyclerViewAdapter.submitList(filterSelectedUsers(allUsers));
                                }

                                else{
                                    Snackbar.make(view, "Please select at least one user", Snackbar.LENGTH_LONG).show();
                                }
                            }
                            catch(NumberFormatException e) {
                                if (binding.billAmountEditText.getText().toString().trim().isEmpty()) {//this is to avoid the snackbar when user clears the text

                                } else {
                                    Snackbar.make(view, "Amount can only be numbers and decimal", Snackbar.LENGTH_LONG).show();
                                }
                            }

                            binding.billAmountEditText.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                }

                                @Override
                                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                }

                                @Override
                                public void afterTextChanged(Editable editable) {
                                    try {
                                        Double amount = Double.parseDouble(editable.toString());
                                        Integer usersCount = getSelectedUsersCount(allUsers);
                                        if(usersCount > 0) {
                                            amount = amount / usersCount;
                                            assignBillRecyclerViewAdapter = new AssignBillRecyclerViewAdapter(getSplitType(), amount);
                                            binding.assignBillRecyclerView.setAdapter(assignBillRecyclerViewAdapter);
                                            assignBillRecyclerViewAdapter.submitList(filterSelectedUsers(allUsers));
                                        }

                                        else{
                                            Snackbar.make(view, "Please select at least one user", Snackbar.LENGTH_LONG).show();
                                        }
                                    }
                                catch(NumberFormatException e) {
                                    if (binding.billAmountEditText.getText().toString().trim().equals("")) {//this is to avoid the snackbar when user clears the text

                                    } else {
                                        Snackbar.make(view, "Amount can only be numbers and decimal", Snackbar.LENGTH_LONG).show();
                                    }
                                }
                                }

                            });


                        }

                    } else if (tab.getPosition() == 1) {
                        usersCheckboxAdapter.submitList(filterAllUsers(allUsers));
                        Snackbar.make(view, "Splitting by amount", Snackbar.LENGTH_LONG).show();
                        binding.enterSomethingText.setText("Enter Amount");
                        setSplitType("By Amount");
                        if (group != null) {
                            ///create the adapter AND set it to the recycler view with selected users
                            assignBillRecyclerViewAdapter = new AssignBillRecyclerViewAdapter(getSplitType());
                            binding.assignBillRecyclerView.setAdapter(assignBillRecyclerViewAdapter);
                            HashMap<User, Boolean>  selectedUsers = usersCheckboxAdapter.getAllUsers();
                            assignBillRecyclerViewAdapter.submitList(filterSelectedUsers(selectedUsers));
                        }


                    } else if (tab.getPosition() == 2) {
                        usersCheckboxAdapter.submitList(filterAllUsers(allUsers));
                        Snackbar.make(view, "Splitting by percentage", Snackbar.LENGTH_LONG).show();
                        binding.enterSomethingText.setText("Enter percentage");
                        setSplitType("By Percentage");
                        if (group != null) {
                            //create the adapter AND set it to the recycler view with selected users
                            assignBillRecyclerViewAdapter = new AssignBillRecyclerViewAdapter(getSplitType());
                            binding.assignBillRecyclerView.setAdapter(assignBillRecyclerViewAdapter);
                            HashMap<User, Boolean>  selectedUsers = usersCheckboxAdapter.getAllUsers();
                            assignBillRecyclerViewAdapter.submitList(filterSelectedUsers(selectedUsers));
                        }

                    } else {
                        Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_LONG).show();
                        splitType = "Invalid";
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }

        binding.refreshImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignBillRecyclerViewAdapter = new AssignBillRecyclerViewAdapter(getSplitType());
                binding.assignBillRecyclerView.setAdapter(assignBillRecyclerViewAdapter);
                assignBillRecyclerViewAdapter.submitList(filterSelectedUsers(allUsers));

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public String getSplitType() {
        return splitType;
    }

    public void setSplitType(String splitType) {
        this.splitType = splitType;
    }

    public List<User> filterSelectedUsers(HashMap<User, Boolean> allUsers){
        List<User> selectedUsers = new ArrayList<User>();
        for (Map.Entry<User, Boolean> entry : allUsers.entrySet()) {
         if(entry.getValue()){
             selectedUsers.add(entry.getKey());
         }
        }
        return selectedUsers;
    }

    public List<User> filterAllUsers(HashMap<User, Boolean> allUsers){
        List<User> users = new ArrayList<User>();
        for (Map.Entry<User, Boolean> entry : allUsers.entrySet()) {
            users.add(entry.getKey());
        }
        return users;
    }

    public Integer getSelectedUsersCount(HashMap<User, Boolean> allUsers) {
        int count = 0;
        for (Map.Entry<User, Boolean> entry : allUsers.entrySet()) {
            if (entry.getValue()) {
                count++;
            }
        }
        return count;
    }


}