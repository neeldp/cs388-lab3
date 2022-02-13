package com.codepath.debuggingchallenges.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.debuggingchallenges.R;
import com.codepath.debuggingchallenges.adapters.MoviesAdapter;
import com.codepath.debuggingchallenges.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MoviesActivity extends AppCompatActivity {

    private static final String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";;

    RecyclerView rvMovies;
    MoviesAdapter adapter;
    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        movies = new ArrayList<>();
        rvMovies = findViewById(R.id.rvMovies);

        // Create the adapter to convert the array to views
        adapter = new MoviesAdapter(movies);

        // Attach the adapter to a ListView
        rvMovies.setAdapter(adapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        Log.i(MoviesActivity.class.getSimpleName(), "Movies: " + rvMovies.toString());

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON response) {
                JSONObject jsonObject = response.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(results));
                    Log.i(MoviesActivity.class.getSimpleName(), "Results: " + results);
                    //Log.i(MoviesActivity.class.getSimpleName(), "Movies: " + movies.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.e(MoviesActivity.class.getSimpleName(), "Error retrieving movies: ", throwable);
            }
        });
    }
}
