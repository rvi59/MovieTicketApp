package com.example.firemoviesapp.Apis;

import com.example.firemoviesapp.Models.MovieModel;
import com.example.firemoviesapp.Models.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieInterface {




    @GET("movie/popular")
    Call<MovieModel> getPopmovies(
            @Query("apikey") String apikey
    );


}
