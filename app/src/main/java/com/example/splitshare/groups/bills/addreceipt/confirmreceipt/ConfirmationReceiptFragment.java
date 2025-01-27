package com.example.splitshare.groups.bills.addreceipt.confirmreceipt;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splitshare.R;
import com.example.splitshare.databinding.ConfirmationReceiptFragmentBinding;
import com.example.splitshare.groups.bills.Receipt;
import com.example.splitshare.groups.splitbill.SplitBillDetails;
import com.example.splitshare.login.user.LoggedInUser;
import com.example.splitshare.login.user.User;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfirmationReceiptFragment extends Fragment {

    private ConfirmationReceiptFragmentBinding binding;
    private ConfirmationReceiptViewModel mViewModel;
    private ConfirmReceiptDetailedReceiptClass confirmReceiptDetailedReceiptClass;

    public static ConfirmationReceiptFragment newInstance() {
        return new ConfirmationReceiptFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ConfirmationReceiptFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ConfirmationReceiptViewModel.class);

        Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey("RECEIPT")){
            confirmReceiptDetailedReceiptClass = (ConfirmReceiptDetailedReceiptClass) bundle.getSerializable("RECEIPT");
            assert confirmReceiptDetailedReceiptClass != null;
            binding.receiptDescriptionTextView.setText(String.format("%s", confirmReceiptDetailedReceiptClass.getReceiptDescription()));
            binding.receiptAmountTextView.setText(String.format("%s", confirmReceiptDetailedReceiptClass.getReceiptAmount()));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            binding.receiptDateTextView.setText(String.format("%s", simpleDateFormat.format(confirmReceiptDetailedReceiptClass.getReceiptDate())));
            binding.ReceiptAdderTextView.setText(String.format("%s %s", LoggedInUser.getInstance().getUser().getFirstName(), LoggedInUser.getInstance().getUser().getLastName()));
            binding.groupNameTextView.setText(String.format("%s", confirmReceiptDetailedReceiptClass.getGroup().getGroupName().toUpperCase()));
            HashMap<User, Double> amountSplit = confirmReceiptDetailedReceiptClass.getAmountSplit();

            List<UserAndAmountSplitClass> userAndAmountSplitClassList = new ArrayList<>();
            for (Map.Entry<User, Double> entry : amountSplit.entrySet()) {
                UserAndAmountSplitClass userAndAmountSplitClass = new UserAndAmountSplitClass();
                userAndAmountSplitClass.setUser(entry.getKey());
                userAndAmountSplitClass.setAmount(entry.getValue());
                userAndAmountSplitClassList.add(userAndAmountSplitClass);
            }


            binding.amountDividedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.amountDividedRecyclerView.setHasFixedSize(false);
            AmountSplittedViewAdapter amountSplittedViewAdapter = new AmountSplittedViewAdapter();
            binding.amountDividedRecyclerView.setAdapter(amountSplittedViewAdapter);


            amountSplittedViewAdapter.submitList(userAndAmountSplitClassList);

            binding.receiptConfirmationConfirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                         Receipt receipt = new Receipt(confirmReceiptDetailedReceiptClass.getReceiptDescription(), confirmReceiptDetailedReceiptClass.getReceiptAmount(), confirmReceiptDetailedReceiptClass.getReceiptDate(), LoggedInUser.getInstance().getUser().getUserID(), confirmReceiptDetailedReceiptClass.getGroup().getGroupID());
                        Long receiptID = mViewModel.insert(receipt);
                        //now we need to set the split bill details
                        for (Map.Entry<User, Double> entry : amountSplit.entrySet()) {
                            SplitBillDetails splitBillDetails = new SplitBillDetails(entry.getValue(), (int) (long) receiptID, entry.getKey().getUserID(), "assigned");
                            mViewModel.insert(splitBillDetails);
                        }

                    Snackbar.make(view, "Bill Successfully added", Snackbar.LENGTH_LONG).show();
                    NavController navController = Navigation.findNavController(view);
                    navController.popBackStack();
                    navController.popBackStack();
                }
            });

            binding.receiptConfirmationEditBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavController navController = Navigation.findNavController(view);
                    navController.popBackStack();
                }
            });

        }

    }
}