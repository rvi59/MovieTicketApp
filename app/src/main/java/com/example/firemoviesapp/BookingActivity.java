package com.example.firemoviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Objects;

public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        Objects.requireNonNull(getSupportActionBar()).setTitle("Payment");

       String seats = getIntent().getStringExtra("seat");
        Toast.makeText(this, ""+seats, Toast.LENGTH_SHORT).show();




    }
}