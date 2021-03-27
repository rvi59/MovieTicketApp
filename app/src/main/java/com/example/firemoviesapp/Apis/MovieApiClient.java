package com.example.firemoviesapp.Apis;


import com.example.firemoviesapp.Models.Results;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieApiClient {

//    public static final String BASE_URL = "https://api.themoviedb.org/3/movie";
//    public static Call<Results> getMovieApiClient;
//    private static MovieApiClient movieApiClient = null;
//    private MovieInterface movieInterface;
//
//
//    private MovieApiClient(){
//
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        movieInterface = retrofit.create(MovieInterface.class);
//
//    }
//
//
//    public static synchronized MovieApiClient getMovieApiClient() {
//        if (movieApiClient == null) {
//            movieApiClient = new MovieApiClient();
//        }
//        return movieApiClient;
//    }
//
//    public MovieInterface getMovieApi() {
//        return movieInterface;
//    }


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
