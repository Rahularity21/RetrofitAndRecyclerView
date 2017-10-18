package com.ithought.rahul.testing;

import com.ithought.rahul.testing.model.Movie;
import com.ithought.rahul.testing.model.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Rahul on 10/18/2017.
 */

public interface ApiInterface {

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetail(@Path("id") int id, @Query("api_key") String apiKey);

}
