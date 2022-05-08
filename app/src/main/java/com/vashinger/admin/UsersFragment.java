package com.vashinger.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.vashinger.admin.databinding.FragmentUsersBinding;

public class UsersFragment extends Fragment {

    private FragmentUsersBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUsersBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();


        binding.washingPerfBtn.setOnClickListener(view1 -> Navigation.findNavController(view).navigate(R.id.action_usersFragment_to_washingPreferenceControlFragment));
        binding.iron.setOnClickListener(view1 -> Navigation.findNavController(view).navigate(R.id.action_usersFragment_to_ironControlFragment));
        binding.dryCleaning.setOnClickListener(view1 -> Navigation.findNavController(view).navigate(R.id.action_usersFragment_to_dryCleaningControlFragment));
        binding.offersBtn.setOnClickListener(view1 -> Navigation.findNavController(view).navigate(R.id.action_usersFragment_to_offersFragment));
        binding.expressDeliveryBtn.setOnClickListener(view1 -> Navigation.findNavController(view).navigate(R.id.action_usersFragment_to_expressDeliveryFragment));
        binding.washAndIronBtn.setOnClickListener(view1 -> Navigation.findNavController(view).navigate(R.id.action_usersFragment_to_washAndIronFragment));
        binding.washAndFoldBtn.setOnClickListener(view1 -> Navigation.findNavController(view).navigate(R.id.action_usersFragment_to_washAndFoldFragment));
        binding.schedulePerfBtn.setOnClickListener(view1 -> Navigation.findNavController(view).navigate(R.id.action_usersFragment_to_schedulePreferenceFragment));
        return view;

    }
}