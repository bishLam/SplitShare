package com.example.splitshare.groups.bills.settle.owes;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splitshare.databinding.OwedByAndToRecyclerViewItemBinding;

public class OwedByAndToViewHolder extends RecyclerView.ViewHolder {
    OwedByAndToRecyclerViewItemBinding binding;

    public OwedByAndToViewHolder(@NonNull OwedByAndToRecyclerViewItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void update(OwesByAndTo owesByAndTo) {
        if (owesByAndTo.getOwedByName() != null && owesByAndTo.getOwedByLastName() != null && owesByAndTo.getOwedToName() != null && owesByAndTo.getOwedToLastName() != null) {
            binding.owedByText.setText(owesByAndTo.getOwedByName().toString() + " " + owesByAndTo.getOwedByLastName().toString());
            binding.owedToText.setText(owesByAndTo.getOwedToName().toString() + " " + owesByAndTo.getOwedToLastName().toString());
            binding.owedAmountText.setText("$ " + owesByAndTo.getAmount().toString());
        } else {
            binding.owedByText.setText("");
            binding.owedToText.setText("");
            binding.owedAmountText.setText("");
            binding.textView16.setVisibility(View.GONE);
        }

    }
}
