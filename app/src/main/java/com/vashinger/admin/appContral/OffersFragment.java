package com.vashinger.admin.appContral;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vashinger.admin.R;
import com.vashinger.admin.databinding.FragmentOffersBinding;

public class OffersFragment extends Fragment {

    private FragmentOffersBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOffersBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();

        binding.createPromoCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_offersFragment_to_promoCodeListFragment);
            }
        });
        return view;

    }
}