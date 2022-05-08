package com.vashinger.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vashinger.admin.databinding.FragmentNotificationBinding;


public class NotificationFragment extends Fragment {

   private FragmentNotificationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentNotificationBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();

        return view;
    }
}