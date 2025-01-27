package com.example.splitshare.groups.bills.addreceipt.confirmreceipt;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitshare.databinding.ReceiptConfirmationSplittedBillRecyclerViewItemBinding;

public class AmountSplittedViewHolder extends RecyclerView.ViewHolder {

    private final ReceiptConfirmationSplittedBillRecyclerViewItemBinding binding;
    public AmountSplittedViewHolder(@NonNull ReceiptConfirmationSplittedBillRecyclerViewItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void update(UserAndAmountSplitClass userAndAmountSplitClass){
        binding.amountSplittedTextView.setText(String.format("$ %s", userAndAmountSplitClass.getAmount()));
        binding.toUserNameTextView.setText(String.format("%s %s", userAndAmountSplitClass.getUser().getFirstName(), userAndAmountSplitClass.getUser().getLastName()));
    }


}
