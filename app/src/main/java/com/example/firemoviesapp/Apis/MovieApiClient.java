package com.example.firemoviesapp.Apis;


import com.example.firemoviesapp.Models.Results;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieApiClient {




    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static MovieApiClient smovieApiClient;
    private static Retrofit sRetrofit;


    private MovieApiClient(){
        sRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized MovieApiClient getInstance(){
        if (smovieApiClient == null){
            smovieApiClient = new MovieApiClient();
        }
        return smovieApiClient;
    }


    public MovieInterface getApi(){
        return sRetrofit.create(MovieInterface.class);
    }



}
