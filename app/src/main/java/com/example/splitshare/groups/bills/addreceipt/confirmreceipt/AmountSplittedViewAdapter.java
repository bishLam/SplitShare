package com.example.splitshare.groups.bills.addreceipt.confirmreceipt;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.splitshare.databinding.ReceiptConfirmationSplittedBillRecyclerViewItemBinding;

public class AmountSplittedViewAdapter extends ListAdapter<UserAndAmountSplitClass, AmountSplittedViewHolder> {
    public AmountSplittedViewAdapter(){
        super(DIFF_CALLBACK);
    }


    private static  final DiffUtil.ItemCallback<UserAndAmountSplitClass> DIFF_CALLBACK = new DiffUtil.ItemCallback<UserAndAmountSplitClass>() {
        @Override
        public boolean areItemsTheSame(@NonNull UserAndAmountSplitClass oldItem, @NonNull UserAndAmountSplitClass newItem) {
            return oldItem.getUser().getUserID().equals(newItem.getUser().getUserID());
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserAndAmountSplitClass oldItem, @NonNull UserAndAmountSplitClass newItem) {
            return oldItem.getAmount().equals(newItem.getAmount()) &&
                    oldItem.getUser().getFirstName().equals(newItem.getUser().getFirstName()) &&
                    oldItem.getUser().getLastName().equals(newItem.getUser().getLastName())
                    && oldItem.getUser().getEmail().equals(newItem.getUser().getEmail());
        }
    };

    @NonNull
    @Override
    public AmountSplittedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ReceiptConfirmationSplittedBillRecyclerViewItemBinding binding = ReceiptConfirmationSplittedBillRecyclerViewItemBinding.inflate(inflater, parent, false);
        return new AmountSplittedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AmountSplittedViewHolder holder, int position) {
        UserAndAmountSplitClass userAndAmountSplitClass = getItem(position);
        holder.update(userAndAmountSplitClass);
    }
}
