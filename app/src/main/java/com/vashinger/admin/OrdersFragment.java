package com.vashinger.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vashinger.admin.adapter.OrderAdapter;
import com.vashinger.admin.databinding.FragmentOrdersBinding;
import com.vashinger.admin.modal.OrdersModel;

import java.util.ArrayList;

public class OrdersFragment extends Fragment {

   FragmentOrdersBinding binding ;

   ArrayList<OrdersModel> ordersModelArrayList ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrdersBinding.inflate(getLayoutInflater() , container , false);
        View view = binding.getRoot();

        ordersModelArrayList = new ArrayList<>();

        binding.recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL ,false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("orderDetails");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ordersModelArrayList.clear();
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        OrdersModel orderModel = dataSnapshot.getValue(OrdersModel.class);



                        ordersModelArrayList.add(orderModel);
                    }

                    OrderAdapter orderAdapter = new OrderAdapter(getActivity() , ordersModelArrayList);
                    binding.recyclerView.setAdapter(orderAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        binding.filter.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                MenuInflater menuInflater = getActivity().getMenuInflater();
                menuInflater.inflate(R.menu.delivery_boy_option_menu, contextMenu);
                contextMenu.setHeaderTitle("Choose filter");

            }
        });



        return view;

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.latestOrder) {
            binding.filter.setText("Latest Orders");

        } else if (item.getItemId() == R.id.orderFlashed) {
            binding.filter.setText("Order Flashed");
        } else {

            return false;

        }
        return true;
    }
}