package com.vashinger.admin.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.vashinger.admin.R;
import com.vashinger.admin.modal.AddDeliveryBoyModal;

import java.util.ArrayList;

public class DeliveryBoyAdapter extends RecyclerView.Adapter<DeliveryBoyAdapter.ViewHolder> {
    View view;
    Context context;
    ArrayList<AddDeliveryBoyModal> addDeliveryBoyModalArrayList ;

    public DeliveryBoyAdapter(Context context, ArrayList<AddDeliveryBoyModal> addDeliveryBoyModalArrayList) {
        this.context = context;
        this.addDeliveryBoyModalArrayList = addDeliveryBoyModalArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view =LayoutInflater.from(context).inflate(R.layout.display_delivery_boy_layout, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AddDeliveryBoyModal deliveryBoyModal = addDeliveryBoyModalArrayList.get(position);
        holder.deliveryBoyId.setText(deliveryBoyModal.getDeliveryBoyCode());
        holder.deliveryBoyName.setText(deliveryBoyModal.getDeliveryBoyName());
        holder.deliveryBoyNmber.setText(deliveryBoyModal.getDeliveryBoyNumber());
        holder.deliveryBoyStatus.setText(deliveryBoyModal.getDeliveryBoyStatus());



        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("deliveryBoyId" ,deliveryBoyModal.getDeliveryBoyCode());
                bundle.putString("deliveryBoyName" , deliveryBoyModal.getDeliveryBoyName());
                bundle.putString("deliveryBoyNmber" ,deliveryBoyModal.getDeliveryBoyNumber());
                bundle.putString("deliveryBoyStatus" ,deliveryBoyModal.getDeliveryBoyStatus());
                Navigation.findNavController(view).navigate(R.id.action_deliveryBoy_to_deliveryBoyOrdersFragment , bundle);
            }
        });

    }

    @Override
    public int getItemCount() {
        return addDeliveryBoyModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView deliveryBoyId, deliveryBoyName , deliveryBoyNmber, deliveryBoyStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            deliveryBoyId = itemView.findViewById(R.id.deliveryBoyCode);
            deliveryBoyName = itemView.findViewById(R.id.deliveryBoyName);
            deliveryBoyNmber = itemView.findViewById(R.id.deliveryBoyNumber);
            deliveryBoyStatus = itemView.findViewById(R.id.deliveryBoyStatus);
        }
    }
}
