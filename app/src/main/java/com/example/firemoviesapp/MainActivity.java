package com.example.firemoviesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.firemoviesapp.Adapters.MoviesAdapter;
import com.example.firemoviesapp.Apis.MovieApiClient;
import com.example.firemoviesapp.Models.MovieModel;
import com.example.firemoviesapp.Models.Results;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    final String API_KEY = "0e065b761192861b3598e9397c0a1958";
    List<Results> resultsList = new ArrayList<>();
    MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.popRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RetriveMovies(API_KEY);

    }


    public void RetriveMovies(String apiKey) {

        Call<MovieModel> call;

        call = MovieApiClient.getInstance().getApi().getPopmovies(apiKey);

        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.isSuccessful() && response.body().getResults() != null) {

                    resultsList.clear();
                    resultsList = response.body().getResults();
                    moviesAdapter = new MoviesAdapter(MainActivity.this, resultsList);
                    recyclerView.setAdapter(moviesAdapter);
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout:
                mAuth.signOut();
                finish();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

}