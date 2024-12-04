package com.example.splitshare.groups.bills.showreceipts;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.splitshare.databinding.ShowBillsRecyclerViewItemBinding;
import com.example.splitshare.groups.bills.Receipt;

public class ShowReceiptViewAdapter extends ListAdapter<DisplayReceiptClass, ShowReceiptsViewHolder> {
    private ShowBillsRecyclerViewItemBinding binding;

    public ShowReceiptViewAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<DisplayReceiptClass> DIFF_CALLBACK = new DiffUtil.ItemCallback<DisplayReceiptClass>() {
        @Override
        public boolean areItemsTheSame(@NonNull DisplayReceiptClass oldItem, @NonNull DisplayReceiptClass newItem) {
            return
                    oldItem.getReceiptID().equals(newItem.getReceiptID());
        }

        @Override
        public boolean areContentsTheSame(@NonNull DisplayReceiptClass oldItem, @NonNull DisplayReceiptClass newItem) {
            return
                    oldItem.getUserID().equals(newItem.getUserID()) &&
                            oldItem.getGroupID().equals(newItem.getGroupID()) &&
                            oldItem.getReceiptAmount().equals(newItem.getReceiptAmount()) &&
                            oldItem.getReceiptDate().equals(newItem.getReceiptDate()) &&
                            oldItem.getReceiptDescription().equals(newItem.getReceiptDescription())
                    ;
        }
    };

    @NonNull
    @Override
    public ShowReceiptsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ShowBillsRecyclerViewItemBinding.inflate(inflater, parent, false);
        return new ShowReceiptsViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ShowReceiptsViewHolder holder, int position) {
        DisplayReceiptClass receipt = getItem(position);
        holder.update(receipt);
    }
}
