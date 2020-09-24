package com.codepath.flixster;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.flixster.adapter.MovieAdapter;
import com.codepath.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    private static final String NOW_PLAYING_URL =
            "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String TAG = "MainActivity";
    RecyclerView rvMovies;
    MovieAdapter adapter;
    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies = new ArrayList<Movie>();
        // Lookup the recyclerview in activity layout
        rvMovies = (RecyclerView) findViewById(R.id.recyclerView);
        // Create adapter passing in the movie data
        adapter = new MovieAdapter(this, movies);
        // Attach the adapter to the recyclerview to populate items
        rvMovies.setAdapter(adapter);
        // Set layout manager to position the items
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvMovies.setLayoutManager(linearLayoutManager);

        getNowPlayingMovies();
        Log.i(TAG, "Movies " + movies.size());
    }

    private void getNowPlayingMovies(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON response) {
                        Log.d(TAG, "onSuccess");
                        JSONObject jsonObject = response.jsonObject;
                        try {
                            JSONArray results = jsonObject.getJSONArray("results");
                            movies.addAll(Movie.fromJsonArray(results));
                            adapter.notifyDataSetChanged();
                            Log.i(TAG, "Movies " + movies.size());
                        } catch (JSONException e) {
                            Log.e(TAG, "Hit JSON exception", e);
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String errorResponse, Throwable t) {
                        Log.e(TAG, "onFailure", t);
                    }
                }
        );
    }
}
