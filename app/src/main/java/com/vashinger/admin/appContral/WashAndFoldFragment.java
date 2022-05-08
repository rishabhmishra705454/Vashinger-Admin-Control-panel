package com.vashinger.admin.appContral;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vashinger.admin.R;
import com.vashinger.admin.databinding.FragmentWashAndFoldBinding;

public class WashAndFoldFragment extends Fragment {

   FragmentWashAndFoldBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentWashAndFoldBinding.inflate(getLayoutInflater(), container , false);
        View view = binding.getRoot();


        binding.backId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });
        return view;

    }
}