package com.vashinger.admin;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.vashinger.admin.databinding.FragmentDeliveryBoyBinding;
import com.vashinger.admin.modal.AddDeliveryBoyModal;
import com.vashinger.admin.modal.OrdersModel;

import java.util.ArrayList;

public class deliveryBoy extends Fragment {


    FragmentDeliveryBoyBinding binding;

    ArrayList<AddDeliveryBoyModal> deliveryBoyModalArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDeliveryBoyBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();

        deliveryBoyModalArrayList = new ArrayList<>();

        binding.recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL ,false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("DeliveryBoy");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                deliveryBoyModalArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    AddDeliveryBoyModal orderModel = dataSnapshot.getValue(AddDeliveryBoyModal.class);



                    deliveryBoyModalArrayList.add(orderModel);
                }
                DeliveryBoyAdapter deliveryBoyAdapter = new DeliveryBoyAdapter(view.getContext(), deliveryBoyModalArrayList);
                binding.recyclerView.setAdapter(deliveryBoyAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_deliveryBoy_to_addDeliveryBoyFragment);
            }
        });

        binding.backId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });

        return view;
    }


}