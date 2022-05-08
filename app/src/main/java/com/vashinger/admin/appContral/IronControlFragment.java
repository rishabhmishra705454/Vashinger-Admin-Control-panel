package com.vashinger.admin.appContral;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vashinger.admin.R;
import com.vashinger.admin.adapter.DryCleaningControlAdapter;
import com.vashinger.admin.databinding.FragmentIronControlBinding;
import com.vashinger.admin.modal.DryCleaningControlModal;

import java.util.ArrayList;

public class IronControlFragment extends Fragment {


    private FragmentIronControlBinding binding;
    private ArrayList<DryCleaningControlModal> dryCleaningControlModalList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentIronControlBinding.inflate(getLayoutInflater(), container, false);
        View view = binding.getRoot();
        dryCleaningControlModalList = new ArrayList<>();

        binding.dryCleaningRV.setHasFixedSize(true);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 3);

        binding.dryCleaningRV.setLayoutManager(linearLayoutManager);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ironItem");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dryCleaningControlModalList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {


                    DryCleaningControlModal addDetailModel = dataSnapshot.getValue(DryCleaningControlModal.class);
                    dryCleaningControlModalList.add(addDetailModel);
                    DryCleaningControlAdapter addDetailAdapter = new DryCleaningControlAdapter(getActivity(), dryCleaningControlModalList);
                    binding.dryCleaningRV.setAdapter(addDetailAdapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.dry_cleaning_item_edit_dialog_layout,null);
                builder.setView(view1);

                builder.setCancelable(true);
                TextInputLayout title, price, image;
                title = view1.findViewById(R.id.title);
                price = view1.findViewById(R.id.price);
                image = view1.findViewById(R.id.image);
                MaterialButton add = view1.findViewById(R.id.addItemBtn);

                AlertDialog alertDialog = builder.create();
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String mTitle = title.getEditText().getText().toString();
                        String mPrice = price.getEditText().getText().toString();
                        String mImage = image.getEditText().getText().toString();

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ironItem");

                        DryCleaningControlModal dryCleaningControlModal = new DryCleaningControlModal(mImage,mTitle,mPrice,"0","add");

                        reference.child(mTitle).setValue(dryCleaningControlModal);
                        Toast.makeText(getContext(), "Item Added", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();

                    }
                });
                alertDialog.show();
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