package com.example.splitshare.homepage;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitshare.databinding.HomePageFragmentBinding;
import com.example.splitshare.databinding.HomepageBillsRecyclerViewItemBinding;
import com.example.splitshare.groups.bills.Receipt;
import com.example.splitshare.room.SplitShareRepository;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class HomePageBillsViewHolder extends RecyclerView.ViewHolder {
    private HomepageBillsRecyclerViewItemBinding binding;

    public HomePageBillsViewHolder(HomepageBillsRecyclerViewItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void update(DetailedReceiptClass receipt) {
        String pattern = "EEE, MMM d";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        DecimalFormat format = new DecimalFormat("#.00");

        binding.amountTextView.setText("$" + format.format(receipt.getAmount()) + " Total");
        binding.billByTextView.setText("By " + receipt.getFirstName().substring(0, 1).toUpperCase() + receipt.getFirstName().substring(1));
        binding.dateTextView.setText(simpleDateFormat.format(receipt.getReceiptDate()));
        binding.homePageGroupNameTextView.setText(receipt.getGroupName().toString());

    }
}
