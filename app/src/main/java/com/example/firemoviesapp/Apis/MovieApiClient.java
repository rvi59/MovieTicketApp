package com.example.firemoviesapp.Apis;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieApiClient {

    public static final String BASE_URL = "https://api.themoviedb.org/3/movie";
    private static MovieApiClient movieApiClient = null;
    private MovieInterface movieInterface;


    private MovieApiClient(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieInterface = retrofit.create(MovieInterface.class);

    }


    public static synchronized MovieApiClient getMovieApiClient() {
        if (movieApiClient == null) {
            movieApiClient = new MovieApiClient();
        }
        return movieApiClient;
    }

    public MovieInterface getMovieApi() {
        return movieInterface;
    }

}
