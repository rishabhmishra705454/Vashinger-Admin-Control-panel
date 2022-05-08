package com.vashinger.admin.appContral;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vashinger.admin.R;
import com.vashinger.admin.databinding.FragmentWashingPreferenceControlBinding;

import java.util.HashMap;

public class washingPreferenceControlFragment extends Fragment {

    private FragmentWashingPreferenceControlBinding binding;

    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWashingPreferenceControlBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

        progressDialog.show();

        //fetching data from database

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("settings");

        myRef.child("washingPreferenceSetting").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDialog.show();

                boolean isColorPreference = snapshot.child("isColorPreference").getValue(Boolean.class);
                boolean isWashingTemp = snapshot.child("isWashingTemp").getValue(Boolean.class);
                boolean isDryHeater = snapshot.child("isDryHeater").getValue(Boolean.class);
                boolean isScentedDetergent = snapshot.child("isScentedDetergent").getValue(Boolean.class);
                boolean isUseSoftner = snapshot.child("isUseSoftner").getValue(Boolean.class);

                boolean isAdditionalNote = snapshot.child("isAdditionalNote").getValue(Boolean.class);
                String dryHeaterPrice = snapshot.child("dryHeaterPrice").getValue(String.class);
                String scentedDetergentPrice = snapshot.child("scentedDetergentPrice").getValue(String.class);
                String useSoftnerPrice = snapshot.child("useSoftnerPrice").getValue(String.class);


                if (isColorPreference){
                    binding.colorPrefBtn.setChecked(true);
                }else {
                    binding.colorPrefBtn.setChecked(false);
                }

                if (isWashingTemp){
                    binding.washingTempBtn.setChecked(true);
                }else {
                    binding.washingTempBtn.setChecked(false);
                }

                if (isDryHeater){
                    binding.dryHeaterBtn.setChecked(true);
                }else {
                    binding.dryHeaterBtn.setChecked(false);
                }

                if (isScentedDetergent){
                    binding.scentedDetergentBtn.setChecked(true);
                }else {
                    binding.scentedDetergentBtn.setChecked(false);
                }

                if (isUseSoftner){
                    binding.userSoftnerBtn.setChecked(true);
                }else {
                    binding.userSoftnerBtn.setChecked(false);
                }

                if (isAdditionalNote){
                    binding.additionalNoteBtn.setChecked(true);
                }else {
                    binding.additionalNoteBtn.setChecked(false);
                }

                binding.dryHeaterPrice.setText(dryHeaterPrice);
                binding.scentedDetergentPrice.setText(scentedDetergentPrice);
                binding.userSoftnerPrice.setText(useSoftnerPrice);


                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                progressDialog.dismiss();
            }
        });


        binding.dryHeaterEdit.setOnClickListener(new View.OnClickListener() {
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
                        binding.dryHeaterPrice.setText(getPrice);
                        alertDialog.dismiss();
                    }
                });


                alertDialog.show();

            }
        });
        binding.scentedDetergentEdit.setOnClickListener(new View.OnClickListener() {
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
                        binding.scentedDetergentPrice.setText(getPrice);
                        alertDialog.dismiss();
                    }
                });


                alertDialog.show();

            }
        });
        binding.userSoftnerEdit.setOnClickListener(new View.OnClickListener() {
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
                        binding.userSoftnerPrice.setText(getPrice);
                        alertDialog.dismiss();
                    }
                });


                alertDialog.show();

            }
        });


        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isColorPreference, isWashingTemp, isDryHeater, isScentedDetergent, isUseSoftner, isAdditionalNote;

                if (binding.colorPrefBtn.isChecked()) {
                    isColorPreference = true;
                } else {
                    isColorPreference = false;
                }

                if (binding.washingTempBtn.isChecked()) {
                    isWashingTemp = true;

                } else {
                    isWashingTemp = false;
                }

                if (binding.dryHeaterBtn.isChecked()) {
                    isDryHeater = true;

                } else {
                    isDryHeater = false;
                }


                if (binding.scentedDetergentBtn.isChecked()) {
                    isScentedDetergent = true;

                } else {
                    isScentedDetergent = false;
                }


                if (binding.userSoftnerBtn.isChecked()) {
                    isUseSoftner = true;

                } else {
                    isUseSoftner = false;
                }

                if (binding.additionalNoteBtn.isChecked()) {
                    isAdditionalNote = true;

                } else {
                    isAdditionalNote = false;
                }

                String dryHeaterPrice = binding.dryHeaterPrice.getText().toString().trim();
                String scentedDetergentPrice = binding.scentedDetergentPrice.getText().toString().trim();
                String useSoftnerPrice = binding.userSoftnerPrice.getText().toString().trim();

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("isColorPreference", isColorPreference);
                hashMap.put("isWashingTemp", isWashingTemp);
                hashMap.put("isDryHeater", isDryHeater);
                hashMap.put("isScentedDetergent", isScentedDetergent);
                hashMap.put("isUseSoftner", isUseSoftner);
                hashMap.put("isAdditionalNote", isAdditionalNote);
                hashMap.put("dryHeaterPrice", dryHeaterPrice);
                hashMap.put("scentedDetergentPrice", scentedDetergentPrice);
                hashMap.put("useSoftnerPrice", useSoftnerPrice);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("settings");

                myRef.child("washingPreferenceSetting").updateChildren(hashMap);

            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });


        return view;
    }
}