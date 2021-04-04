package com.example.firemoviesapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.firemoviesapp.Models.Results;
import com.haerul.bottomfluxdialog.BottomFluxDialog;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class BookingActivity extends AppCompatActivity {

    ImageView imageViewPoster, imageViewCalender, imageViewTime;
    TextView textViewName, textViewCalender, textViewTime, textViewSeat, textViewTotal;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private String date;
    private Button  buttonPay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        Objects.requireNonNull(getSupportActionBar()).setTitle("Payment");

        String seats = getIntent().getStringExtra("seat");
        //Toast.makeText(this, "" + seats, Toast.LENGTH_SHORT).show();

        Results results = getIntent().getParcelableExtra("details");






        imageViewPoster     = findViewById(R.id.imgPoster);
        imageViewCalender   = findViewById(R.id.imgCalender);
        imageViewTime       = findViewById(R.id.imgTime);
        textViewName        =  findViewById(R.id.movieName);
        textViewCalender    =  findViewById(R.id.tvCalender);
        textViewTime        =  findViewById(R.id.tvTime);
        textViewSeat        =  findViewById(R.id.tvSeat);
        textViewTotal       =  findViewById(R.id.tvTotal);
        buttonPay           =  findViewById(R.id.btnPayment);


        String img_url = results.getBackdrop_path();
        Glide.with(getApplicationContext()).load("https://images.tmdb.org/t/p/w500"+img_url).into(imageViewPoster);
        String title = results.getOriginalTitle();
        textViewName.setText(title);
        textViewSeat.setText(seats);



        imageViewCalender.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        date = dayOfMonth+"-"+month+"-"+year;
                        //Toast.makeText(BookingActivity.this, ""+date, Toast.LENGTH_SHORT).show();
                        textViewCalender.setText(date);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });


        imageViewTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(BookingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mHour = hourOfDay;
                        mMinute = minute;

                        Calendar calendar  = Calendar.getInstance();
                        calendar.set(0,0,0,mHour,mMinute);
                        textViewTime.setText(DateFormat.format("hh:mm",calendar));
                    }
                },12,0,false);
                timePickerDialog.show();
            }
        });


        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomFluxDialog.confirmDialog(BookingActivity.this)

                        .setTextTitle("Confirm Title")
                        .setTextMessage("This is a confirm message")
                        .setImageDialog(R.drawable.poster)
                        .setLeftButtonText("CANCEL")
                        .setRightButtonText("OK")
                        .setConfirmListener(new BottomFluxDialog.OnConfirmListener() {
                            @Override
                            public void onLeftClick() {
                                Toast.makeText(BookingActivity.this, "Left Button Clicked!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onRightClick() {
                                Toast.makeText(BookingActivity.this, "Right Button Clicked!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });






    }
}