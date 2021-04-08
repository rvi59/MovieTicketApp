package com.example.firemoviesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.firemoviesapp.Models.Results;
import com.haerul.bottomfluxdialog.BottomFluxDialog;

import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {


    Button buttonBook;
    ImageView imageView;
    TextView textViewTitle, textViewRating, textViewrelesedate, textViewPlot;

    SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mSharedPreferences = getSharedPreferences("MovieInfo", MODE_PRIVATE);



        Results results = getIntent().getParcelableExtra("details");

        String title = results.getOriginalTitle();
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonBook = findViewById(R.id.btnBook);
        imageView = findViewById(R.id.movieImgfromDetail);
        textViewTitle = findViewById(R.id.titlefromDetails);
        textViewRating = findViewById(R.id.ratingfromDetails);
        textViewrelesedate = findViewById(R.id.releasedatefromDetails);

        textViewPlot = findViewById(R.id.plotfromDetails);


        String movieName = results.getOriginalTitle();
        String img_url = results.getBackdrop_path();
        Glide.with(DetailsActivity.this).load("https://images.tmdb.org/t/p/w500" + img_url).into(imageView);
        textViewTitle.setText(movieName);
        textViewRating.setText(results.getVoteAverage().toString());
        textViewrelesedate.setText(results.getReleaseDate());
        textViewPlot.setText(results.getOverview() + "***************************************************************" +
                "******************************************************************************");


//        Intent intent = new Intent(DetailsActivity.this,SeatActivity.class);
//        intent.putExtra("img",img_url);
//        intent.putExtra("name", movieName);
//        startActivity(intent);


        buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Intent intent = new Intent(DetailsActivity.this,SeatActivity.class);
//                intent.putExtra("img",img_url);
//                intent.putExtra("name", movieName);
//                startActivity(intent);

                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putString("imageName", img_url);
                editor.putString("movName", movieName);
                editor.apply();




                startActivity(new Intent(DetailsActivity.this, SeatActivity.class));
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}