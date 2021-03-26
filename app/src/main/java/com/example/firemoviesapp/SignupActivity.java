package com.example.firemoviesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    EditText editTextName, editTextEmail, editTextPass, editTextNumber;
    RelativeLayout buttonSignup, buttongotoLogin;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;
    String userId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().hide();


        editTextName     = findViewById(R.id.signupName);
        editTextEmail    = findViewById(R.id.signupEmail);
        editTextPass     = findViewById(R.id.signupPassword);
        editTextNumber   = findViewById(R.id.signupPhone);
        buttonSignup     = findViewById(R.id.btnSignup);
        buttongotoLogin  = findViewById(R.id.gotoLogin);
        progressBar      = findViewById(R.id.signupProgress);
        mAuth            = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(SignupActivity.this,MainActivity.class));
            finish();
        }


        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String pass = editTextPass.getText().toString().trim();
                String phone = editTextNumber.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (name.isEmpty()){
                    editTextName.setError("Name is Required");
                }
                else if (email.isEmpty()){
                    editTextEmail.setError("Email is Required");
                }
                else if (!email.matches(emailPattern)){
                    editTextEmail.setError("Enter Valid Email");
                }
                else if (pass.isEmpty()){
                    editTextPass.setError("Password is Required");
                }
                else if (phone.isEmpty()){
                    editTextNumber.setError("Phone number is Required");
                }
                else {

                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                userId = mAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = firebaseFirestore.collection("users").document(userId);
                                Map<String, Object> map = new HashMap<>();
                                map.put("name",name);
                                map.put("email",email);
                                map.put("pass",pass);
                                map.put("phone",phone);
                                documentReference.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(SignupActivity.this, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                });


                            }
                            else {
                                Toast.makeText(SignupActivity.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }

            }
        });


        buttongotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
            }
        });


    }

}