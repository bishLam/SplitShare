package com.example.splitshare.groups.bills.showreceipts;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splitshare.R;
import com.example.splitshare.databinding.ShowBillsFragmentBinding;
import com.example.splitshare.groups.allgroups.Group;

public class ShowBillsFragment extends Fragment {

    private ShowBillsViewModel mViewModel;
    private ShowBillsFragmentBinding binding;
    private Group group;

    public static ShowBillsFragment newInstance() {
        return new ShowBillsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.show_bills_fragment, container, false);
        binding = ShowBillsFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(ShowBillsViewModel.class);
//        // TODO: Use the ViewModel
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ShowBillsViewModel.class);
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey("GROUP")){
            group = (Group) bundle.getSerializable("GROUP");
            binding.groupNameTextView.setText(group.getGroupName());
        }






    }
}