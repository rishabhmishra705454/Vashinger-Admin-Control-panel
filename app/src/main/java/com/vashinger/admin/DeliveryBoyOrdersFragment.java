package com.vashinger.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vashinger.admin.databinding.FragmentDeliveryBoyOrdersBinding;


public class DeliveryBoyOrdersFragment extends Fragment {

   FragmentDeliveryBoyOrdersBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentDeliveryBoyOrdersBinding.inflate(getLayoutInflater() , container , false);
        View view = binding.getRoot();

        String deliveryBoyId = getArguments().getString("deliveryBoyId");
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //No of orders
        DatabaseReference noOfOrders = database.getReference("DeliveryBoy").child(deliveryBoyId);

        noOfOrders.child("orders").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String orderCount = String.valueOf(snapshot.getChildrenCount());
                binding.noOfOrders.setText(orderCount);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //order pickuped


        DatabaseReference orderPickuped = database.getReference("DeliveryBoy").child(deliveryBoyId).child("orders");
        Query queryPickup = orderPickuped.child("status").equalTo("Pickuped");
        queryPickup.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String orderCount = String.valueOf(snapshot.getChildrenCount());
                binding.noOfOrderPickup.setText(orderCount);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });


        return view;
    }
}