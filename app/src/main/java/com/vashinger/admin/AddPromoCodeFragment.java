package com.vashinger.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vashinger.admin.databinding.FragmentAddPromoCodeBinding;
import com.vashinger.admin.modal.PromoCodeModel;

public class AddPromoCodeFragment extends Fragment {

    private  FragmentAddPromoCodeBinding binding ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddPromoCodeBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("PromoCode");


        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.title.getEditText().getText().toString();
                String description = binding.description.getEditText().getText().toString();
                String minOrderPrice = binding.minOrderPrice.getEditText().getText().toString();
                String discountPercentage = binding.discountPercentage.getEditText().getText().toString();
                String maxDiscountPrice = binding.maximumDiscount.getEditText().getText().toString();
                String promoCode = binding.promoCode.getEditText().getText().toString();

                PromoCodeModel promoCodeModel = new PromoCodeModel(title,description,minOrderPrice,discountPercentage, maxDiscountPrice,promoCode);

                myRef.child(promoCode).setValue(promoCodeModel);
                Toast.makeText(view.getContext(), "Added", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(view).popBackStack();
            }
        });
        return view ;
    }
}