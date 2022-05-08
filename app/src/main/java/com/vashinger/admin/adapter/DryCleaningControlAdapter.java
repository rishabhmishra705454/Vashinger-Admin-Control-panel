package com.vashinger.admin.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.vashinger.admin.R;
import com.vashinger.admin.modal.DryCleaningControlModal;

import java.util.ArrayList;

public class DryCleaningControlAdapter extends RecyclerView.Adapter<DryCleaningControlAdapter.ViewHolder>{


    private FirebaseAuth mAuth;
    private Context context;
    private ArrayList<DryCleaningControlModal> addDetailModelArrayList;

    public DryCleaningControlAdapter(Context context, ArrayList<DryCleaningControlModal> addDetailModelArrayList) {
        this.context = context;
        this.addDetailModelArrayList = addDetailModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dry_cleaning_item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DryCleaningControlModal addDetailModel = addDetailModelArrayList.get(position);
        holder.title.setText(addDetailModel.getTitle());
        holder.price.setText(addDetailModel.getPrice());
        Picasso.get().load(addDetailModel.getImage()).into(holder.image);


        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("You you want to delete item from cart");
                builder.setCancelable(true);
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("dryCleaningItem");
                        reference.child(addDetailModel.getTitle()).removeValue();
                        Toast.makeText(context, "Item Has been deleted", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("Cancil", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return addDetailModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView price;
        TextView title;

        ImageButton  deleteBtn ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.textView);
            price = itemView.findViewById(R.id.price);
            deleteBtn = itemView.findViewById(R.id.deleteItem);
        }
    }
}
