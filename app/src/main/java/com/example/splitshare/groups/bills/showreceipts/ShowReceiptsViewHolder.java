package com.example.splitshare.groups.bills.showreceipts;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitshare.databinding.ShowBillsRecyclerViewItemBinding;
import com.example.splitshare.groups.bills.Receipt;

import java.text.SimpleDateFormat;

public class ShowReceiptsViewHolder extends RecyclerView.ViewHolder {
    private ShowBillsRecyclerViewItemBinding binding;

    public ShowReceiptsViewHolder(@NonNull ShowBillsRecyclerViewItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void update(DisplayReceiptClass receipt) {
        String pattern = "EEE, MMM d";
        String username = receipt.getFirstName().substring(0, 1).toUpperCase() + receipt.getFirstName().substring(1) + " " + receipt.getLastName().substring(0, 1).toUpperCase() + receipt.getLastName().substring(1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        binding.receiptDateTextView.setText(simpleDateFormat.format(receipt.getReceiptDate()));
        binding.receiptAmountTextView.setText(receipt.getReceiptAmount().toString());
        binding.receiptDescriptionTextView.setText(receipt.getReceiptDescription());
        binding.receiptSpenderText.setText(username + " added the bill");
    }


}
