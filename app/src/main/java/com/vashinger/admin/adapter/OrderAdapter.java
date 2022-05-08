package com.vashinger.admin.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vashinger.admin.R;
import com.vashinger.admin.modal.DeliveryBoyFlashModal;
import com.vashinger.admin.modal.OrdersModel;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    Context context ;
    ArrayList<OrdersModel> ordersModelArrayList ;
    View view;
    public OrderAdapter(Context context, ArrayList<OrdersModel> ordersModelArrayList) {
        this.context = context;
        this.ordersModelArrayList = ordersModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_boy_layout, parent, false);

        return new OrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        OrdersModel ordersModel = ordersModelArrayList.get(position);

        if (!ordersModel.getDeliveryBoy().isEmpty()){
            holder.deiveyBoy.setText(ordersModel.getDeliveryBoy());
            holder.deliveryFlash.setEnabled(false);
        }

        holder.orderNo.setText(ordersModel.getId());
        holder.pickupDT.setText(ordersModel.getPickupDate() + " | " + ordersModel.getPickupTime());
        holder.deliveryDT.setText(ordersModel.getDeliveryDate() + " | " + ordersModel.getDeliveryTime());

        String uid = ordersModel.getUid();
        String id = ordersModel.getId();


      holder.more.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              Bundle bundle = new Bundle();
              bundle.putString("uid",uid);
              bundle.putString("id" ,id);

              Navigation.findNavController(view).navigate(R.id.action_ordersFragment2_to_orderDetailFragment , bundle);
          }
      });

      holder.deliveryFlash.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

             String deliveryBoyId =  holder.deiveyBoy.getText().toString();

             String orderId = ordersModel.getId();
              FirebaseDatabase mdatabase = FirebaseDatabase.getInstance();

              DatabaseReference ref = mdatabase.getReference("DeliveryBoy").child(deliveryBoyId).child("orders");
              DeliveryBoyFlashModal deliveryBoyFlashModal = new DeliveryBoyFlashModal(orderId , "Confirmed");
              ref.child(orderId).setValue(deliveryBoyFlashModal);

              DatabaseReference ref2 = mdatabase.getReference("orderDetails").child(orderId);
              ref2.child("deliveryBoy").setValue(deliveryBoyId);

              Toast.makeText(view.getContext(), "Order Flashed to Delivery Boy", Toast.LENGTH_SHORT).show();


          }
      });

      holder.laundryFlash.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {



          }
      });

    }

    @Override
    public int getItemCount() {
        return ordersModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView orderNo , pickupDT, deliveryDT , more ;
        EditText deiveyBoy , laundry;
        Button deliveryFlash , laundryFlash;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderNo = itemView.findViewById(R.id.textView3);
            pickupDT = itemView.findViewById(R.id.pickupDT);
            deliveryDT = itemView.findViewById(R.id.textView5);
            deiveyBoy = itemView.findViewById(R.id.editText);
            laundry = itemView.findViewById(R.id.editText2);
            deliveryFlash = itemView.findViewById(R.id.pickupFlash);
            laundryFlash = itemView.findViewById(R.id.laundryFlash);
            more = itemView.findViewById(R.id.more);
        }
    }
}
