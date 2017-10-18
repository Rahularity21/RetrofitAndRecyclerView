package com.ithought.rahul.testing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.ithought.rahul.testing.model.Movie;
import com.ithought.rahul.testing.model.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = ""; /*YOUR API KEY HERE*/
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ApiInterface apiService = ApiClient.getApiClient().create(ApiInterface.class);
        Call<MovieResponse> call =  apiService.getTopRatedMovies(API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                int total_pages = response.body().getTotalPages();
                //Toast.makeText(getApplicationContext(),movies.size(),Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Number of movies received: " + movies.size());
                Log.d(TAG, "Total Pages " + total_pages);
                recyclerView.setAdapter(new MovieAdapter(movies,R.layout.list_item_movie,getApplicationContext()));


            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

//        Call<MovieResponse> detail = apiService.getMovieDetail(19404,API_KEY);
//        detail.enqueue(new Callback<MovieResponse>() {
//            @Override
//            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<MovieResponse> call, Throwable t) {
//
//            }
//        });
    }
}
