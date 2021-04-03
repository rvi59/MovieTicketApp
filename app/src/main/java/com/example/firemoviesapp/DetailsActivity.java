package com.example.firemoviesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

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



        String img_url = results.getBackdrop_path();
        Glide.with(getApplicationContext()).load("https://images.tmdb.org/t/p/w500"+img_url).into(imageView);
        textViewTitle.setText(results.getOriginalTitle());
        textViewRating.setText(results.getVoteAverage().toString());
        textViewrelesedate.setText(results.getReleaseDate());
        textViewPlot.setText(results.getOverview()+"***************************************************************" +
                "******************************************************************************");


        buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BottomFluxDialog.confirmDialog(DetailsActivity.this)
//                        .setTextTitle("Confirm Title")
//                        .setTextMessage("This is a confirm message")
//                        .setImageDialog(R.drawable.poster)
//                        .setLeftButtonText("CANCEL")
//                        .setRightButtonText("OK")
//                        .setConfirmListener(new BottomFluxDialog.OnConfirmListener() {
//                            @Override
//                            public void onLeftClick() {
//                                Toast.makeText(DetailsActivity.this, "Left Button Clicked!", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onRightClick() {
//                                Toast.makeText(DetailsActivity.this, "Right Button Clicked!", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .show();
                startActivity(new Intent(DetailsActivity.this, SeatActivity.class));
            }
        });




    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}