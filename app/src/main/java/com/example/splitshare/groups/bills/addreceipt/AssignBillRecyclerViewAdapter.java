package com.example.splitshare.groups.bills.addreceipt;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.splitshare.databinding.AssignBillRecyclerViewItemBinding;
import com.example.splitshare.login.user.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;

public class AssignBillRecyclerViewAdapter extends ListAdapter<User, AssignBillViewHolder> {
    private AssignBillRecyclerViewItemBinding binding;
    private final HashMap<Integer, Double> amountHash = new HashMap<>();
    private final HashMap<Integer, Double> percentageHash = new HashMap<>();

    String splitType;
    Double splittingAmount;


    protected AssignBillRecyclerViewAdapter(String splitType) {
        super(DIFF_CALLBACK);
        this.splitType = splitType;

    }

    protected AssignBillRecyclerViewAdapter(String splitType, Double splittingAmount) {
        super(DIFF_CALLBACK);
        this.splitType = splitType;
        this.splittingAmount = splittingAmount;
    }
    public static final DiffUtil.ItemCallback<User> DIFF_CALLBACK = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return (oldItem.getUserID().equals(newItem.getUserID()));
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return
                    oldItem.getFirstName().equals(newItem.getFirstName()) &&
                            oldItem.getLastName().equals(newItem.getLastName()) &&
                            oldItem.getEmail().equals(newItem.getEmail()) &&
                            oldItem.getPassword().equals(newItem.getPassword()) &&
                            oldItem.getPhoneNumber().equals(newItem.getPhoneNumber());

        }
    };

    @NonNull
    @Override
    public AssignBillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = AssignBillRecyclerViewItemBinding.inflate(inflater, parent, false);
        return new AssignBillViewHolder(binding, amountHash);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignBillViewHolder holder, int position) {
        User user = getItem(position);
        binding.amountEditText.setText("0");

        switch (splitType) {
            case "Equally":
                //amount that is being splitted
                if (splittingAmount != null) {
                    binding.amountEditText.setText(splittingAmount.toString());
                    amountHash.put(user.getUserID(), splittingAmount);
                } else {
                    binding.amountEditText.setText("0");
                }
                binding.amountEditText.setEnabled(false);
                binding.amountEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        try {
                            amountHash.put(user.getUserID(), Double.parseDouble(editable.toString()));
                        } catch (NumberFormatException e) {
                            //the fragment will handle this if invalid input is added
                        }
                    }

                });
                break;
            case "By Amount":
                binding.amountEditText.setEnabled(true);
                binding.amountEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        try {
                            amountHash.put(user.getUserID(), Double.parseDouble(editable.toString()));
                        } catch (NumberFormatException e) {
                            //the fragment will handle this if invalid input is added
                        }
                    }
                });
                break;
            case "By Percentage":
                binding.amountEditText.setEnabled(true);
                binding.amountEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        try{
                            percentageHash.put(user.getUserID(), Double.parseDouble(editable.toString()));
                        }

                        catch (NumberFormatException e){
                            //The fragment will handle this if invalid input is added
                        }
                    }
                });

                break;
            default:
//            Snackbar.make(binding.getRoot(), "Something went wrong", Snackbar.LENGTH_LONG).show();
                break;
        }
        holder.update(user);
    }

    //this is so that we can store the logic in the fragment from the values and keys
    public HashMap<Integer, Double> getAmountHash() {
        return amountHash;
    }

    public HashMap<Integer, Double> getPercentageHash(){
        return percentageHash;
    }

}
