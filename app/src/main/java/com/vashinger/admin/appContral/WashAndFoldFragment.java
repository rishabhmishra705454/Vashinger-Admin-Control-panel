package com.vashinger.admin.appContral;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vashinger.admin.R;
import com.vashinger.admin.databinding.FragmentWashAndFoldBinding;

import java.util.HashMap;

public class WashAndFoldFragment extends Fragment {

   FragmentWashAndFoldBinding binding;
   private boolean isSubscriptionPlan;

   private ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentWashAndFoldBinding.inflate(getLayoutInflater(), container , false);
        View view = binding.getRoot();


        progressDialog  = new ProgressDialog(getContext());
        //fetching data from database
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("settings");

        myRef.child("washAndFoldSetting").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String bundlePrice = snapshot.child("bundleBagPrice").getValue(String.class);
                String multiBagPrice = snapshot.child("multiBagPrice").getValue(String.class);
                boolean mIsSubscriptionPlan = snapshot.child("isSubscriptionPlan").getValue(Boolean.class);

                binding.perBagBundlePrice.setText(bundlePrice);
                binding.multiBagPrice.setText(multiBagPrice);
                if (mIsSubscriptionPlan){
                    binding.subscriptionPlanBtn.setChecked(true);
                }else{
                    binding.subscriptionPlanBtn.setChecked(false);
                }

                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                progressDialog.dismiss();
            }
        });




        binding.perBagBundlePriceEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.price_edit_layout, null);
                builder.setView(view1);

                builder.setCancelable(true);
                AlertDialog alertDialog = builder.create();
                TextInputLayout price = view1.findViewById(R.id.mPrice);
                Button button = view1.findViewById(R.id.doneBtn);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String getPrice = price.getEditText().getText().toString().trim();
                        binding.perBagBundlePrice.setText(getPrice);
                        alertDialog.dismiss();
                    }
                });


                alertDialog.show();

            }
        });

        binding.multiBagPriceEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.price_edit_layout, null);
                builder.setView(view1);

                builder.setCancelable(true);
                AlertDialog alertDialog = builder.create();
                TextInputLayout price = view1.findViewById(R.id.mPrice);
                Button button = view1.findViewById(R.id.doneBtn);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String getPrice = price.getEditText().getText().toString().trim();
                        binding.multiBagPrice.setText(getPrice);
                        alertDialog.dismiss();
                    }
                });


                alertDialog.show();

            }
        });


        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();

                String bundlePrice = binding.perBagBundlePrice.getText().toString().trim();
                String multiPrice = binding.multiBagPrice.getText().toString().trim();

                if (binding.subscriptionPlanBtn.isChecked()){
                    isSubscriptionPlan = true;
                }else {
                    isSubscriptionPlan = false;
                }

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("bundleBagPrice",bundlePrice);
                hashMap.put("multiBagPrice",multiPrice);
                hashMap.put("isSubscriptionPlan",isSubscriptionPlan);


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("settings");

                myRef.child("washAndFoldSetting").updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(getContext(), "changes saved", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });

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