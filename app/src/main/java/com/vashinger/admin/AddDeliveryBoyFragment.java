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
import com.vashinger.admin.databinding.FragmentAddDeliveryBoyBinding;
import com.vashinger.admin.databinding.FragmentDeliveryBoyBinding;
import com.vashinger.admin.modal.AddDeliveryBoyModal;

import java.util.ArrayList;

public class AddDeliveryBoyFragment extends Fragment {

    FragmentAddDeliveryBoyBinding binding;

    ArrayList<AddDeliveryBoyModal> addDeliveryBoyModalArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentAddDeliveryBoyBinding.inflate(getLayoutInflater(), container , false);
        View view = binding.getRoot();

        addDeliveryBoyModalArrayList = new ArrayList<>();
        addDeliveryBoyModalArrayList.clear();



        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("DeliveryBoy");
                String deliveryBoyCode = binding.deliveryBoyCode.getText().toString().trim();
                String deliveryBoyName = binding.deliveryBoyCodeName.getText().toString();
                String deliveryBoyNumber = binding.deliveryBoyNumber.getText().toString().trim();
                String deliveryBoyStatus = binding.deliveryBoyStatus.getText().toString().trim();
                AddDeliveryBoyModal deliveryBoyModal = new AddDeliveryBoyModal(deliveryBoyCode,deliveryBoyName,deliveryBoyNumber, deliveryBoyStatus);

                myRef.child(deliveryBoyCode).setValue(deliveryBoyModal);

                Toast.makeText(getContext(), "Submitted Successfully", Toast.LENGTH_SHORT).show();

                Navigation.findNavController(view).popBackStack();


            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        return view;
    }
}