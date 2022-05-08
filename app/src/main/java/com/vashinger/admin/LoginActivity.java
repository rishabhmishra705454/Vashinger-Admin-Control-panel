package com.vashinger.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vashinger.admin.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private ProgressDialog progressDialog;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        sharedPreferences = getSharedPreferences("USERDETAILS",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (sharedPreferences.getBoolean("isUserLoggined",false)){

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!validateUserName() || !validatePassword()) {
                    return;
                }
                progressDialog.show();

                String userName = binding.inputEmail.getEditText().getText().toString();
                String password = binding.inputPassword.getEditText().getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("settings");

                myRef.child("login").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String mUserName = snapshot.child("userName").getValue(String.class);
                        String mPassword = snapshot.child("password").getValue(String.class);
                        
                        if (userName.equals(mUserName) && password.equals(mPassword)){

                            editor.putBoolean("isUserLoggined", true);
                            editor.apply();
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Loggined", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Please enter correct detail", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        progressDialog.dismiss();

                    }
                });



               
            }
        });

    }

    private boolean validatePassword() {
        String val = binding.inputPassword.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            binding.inputPassword.setError("Please enter password");
            return false;
        } else {
            binding.inputPassword.setError(null);
            binding.inputPassword.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUserName() {
        String val = binding.inputEmail.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            binding.inputEmail.setError("Please enter user name");
            return false;
        } else {
            binding.inputEmail.setError(null);
            binding.inputEmail.setErrorEnabled(false);
            return true;
        }

    }
}