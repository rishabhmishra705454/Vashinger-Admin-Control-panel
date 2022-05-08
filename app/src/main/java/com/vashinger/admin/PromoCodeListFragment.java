package com.vashinger.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vashinger.admin.adapter.DeliveryBoyAdapter;
import com.vashinger.admin.adapter.PromoCodeAdapter;
import com.vashinger.admin.databinding.FragmentPromoCodeListBinding;
import com.vashinger.admin.modal.AddDeliveryBoyModal;
import com.vashinger.admin.modal.PromoCodeModel;

import java.util.ArrayList;

public class PromoCodeListFragment extends Fragment {

    private FragmentPromoCodeListBinding binding;

    ArrayList<PromoCodeModel> promoCodeModelArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =FragmentPromoCodeListBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();
        binding.recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL ,false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        promoCodeModelArrayList = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("PromoCode");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                promoCodeModelArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    PromoCodeModel promoCodeModel = dataSnapshot.getValue(PromoCodeModel.class);



                    promoCodeModelArrayList.add(promoCodeModel);
                    PromoCodeAdapter promoCodeAdapter = new PromoCodeAdapter(view.getContext() , promoCodeModelArrayList);
                    binding.recyclerView.setAdapter(promoCodeAdapter);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.addPromoCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_promoCodeListFragment_to_addPromoCodeFragment);
            }
        });

        return view ;
    }


}