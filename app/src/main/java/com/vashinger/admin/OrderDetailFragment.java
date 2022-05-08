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
import com.google.firebase.database.ValueEventListener;
import com.vashinger.admin.databinding.FragmentOrderDetailBinding;


public class OrderDetailFragment extends Fragment {


    private FragmentOrderDetailBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentOrderDetailBinding.inflate(getLayoutInflater() , container , false);
       View view = binding.getRoot();

       String uid = getArguments().getString("uid");
       String id = getArguments().getString("id");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("orderDetails").child(id);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String id = snapshot.child("id").getValue().toString();
                String orderDate = snapshot.child("orderDate").getValue().toString();
                String orderTime = snapshot.child("orderTime").getValue().toString();
                String paymentType = snapshot.child("paymentType").getValue().toString();
                String status = snapshot.child("status").getValue().toString();
                String colorPreference = snapshot.child("colorPreference").getValue().toString();
                String washingTemperature = snapshot.child("washingTemperature").getValue().toString();
                String additionalNote = snapshot.child("additionalNote").getValue().toString();
                String pickupDate = snapshot.child("pickupDate").getValue().toString();
                String pickupTime = snapshot.child("pickupTime").getValue().toString();
                String deliveryDate = snapshot.child("deliveryDate").getValue().toString();
                String deliveryTime = snapshot.child("deliveryTime").getValue().toString();
                String totalPrice = snapshot.child("totalPrice").getValue().toString();
                String totalItem = snapshot.child("totalItem").getValue().toString();
                String serviceType = snapshot.child("serviceType").getValue().toString();
                String address = snapshot.child("address").getValue().toString();
                String pincode = snapshot.child("pincode").getValue().toString();
                String locality = snapshot.child("locality").getValue().toString();
                String latitude = snapshot.child("latitude").getValue().toString();
                String longitude = snapshot.child("longitude").getValue().toString();
                String phoneNo = snapshot.child("phoneNo").getValue().toString();
                String houseNo = snapshot.child("houseNo").getValue().toString();
                String landmark = snapshot.child("landmark").getValue().toString();
                String fullName = snapshot.child("fullName").getValue().toString();
                String dryHeater = snapshot.child("dryHeater").getValue().toString();
                String scentedDetergent = snapshot.child("scentedDetergent").getValue().toString();
                String useSoftner = snapshot.child("useSoftner").getValue().toString();

                binding.id.setText(id);
                binding.orderDT.setText(orderDate + " | " + orderTime);
                binding.wasingTemp.setText(washingTemperature);
                binding.status.setText(status);
                binding.paymentType.setText(paymentType);
                binding.serviceType.setText(serviceType);
                binding.pickupDT.setText(pickupDate + " | " + pickupTime);
                binding.deliveryDT.setText(deliveryDate + " | " + deliveryTime);
                binding.totalItem.setText(totalItem);
                binding.totalPrice.setText(totalPrice);
                binding.address.setText(address);
                binding.fullName.setText(fullName);
                binding.aditionalNote.setText(additionalNote);
                binding.phoneNo.setText(phoneNo);
                binding.colorPref.setText(colorPreference);
                binding.deliveryDT.setText( deliveryDate + " | " +deliveryTime);
                binding.landmark.setText(landmark);
                binding.dryHeater.setText(dryHeater);
                binding.houseNo.setText(houseNo);
                binding.sentedDetergent.setText(scentedDetergent);
                binding.useSoftner.setText(useSoftner);
                binding.latitude.setText(latitude);
                binding.longitude.setText(longitude);
                binding.pinCode.setText(pincode);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return  view ;


    }
}