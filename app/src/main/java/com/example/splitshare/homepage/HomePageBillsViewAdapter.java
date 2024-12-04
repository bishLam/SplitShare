package com.example.splitshare.homepage;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.splitshare.databinding.HomepageBillsRecyclerViewItemBinding;
import com.example.splitshare.groups.bills.Receipt;
import com.example.splitshare.groups.bills.showreceipts.DisplayReceiptClass;

public class HomePageBillsViewAdapter extends ListAdapter<DetailedReceiptClass, HomePageBillsViewHolder> {
    private HomepageBillsRecyclerViewItemBinding binding;

    public HomePageBillsViewAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<DetailedReceiptClass> DIFF_CALLBACK = new DiffUtil.ItemCallback<DetailedReceiptClass>() {
        @Override
        public boolean areItemsTheSame(@NonNull DetailedReceiptClass oldItem, @NonNull DetailedReceiptClass newItem) {
            return
                    oldItem.getReceiptID().equals(newItem.getReceiptID());
        }

        @Override
        public boolean areContentsTheSame(@NonNull DetailedReceiptClass oldItem, @NonNull DetailedReceiptClass newItem) {
            return
                    oldItem.getAmount().equals(newItem.getAmount()) &&
                            oldItem.getReceiptDate().equals(newItem.getReceiptDate()) &&
                            oldItem.getGroupName().equals(newItem.getGroupName()) &&
                            oldItem.getFirstName().equals(newItem.getFirstName()) &&
                            oldItem.getLastName().equals(newItem.getLastName());
        }
    };

    @NonNull
    @Override
    public HomePageBillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = HomepageBillsRecyclerViewItemBinding.inflate(inflater, parent, false);
        return new HomePageBillsViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull HomePageBillsViewHolder holder, int position) {
        DetailedReceiptClass receipt = getItem(position);
        holder.update(receipt);
    }
}
