package com.codepath.flixster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.flixster.databinding.ActivityMovieDetailsBinding;
import com.codepath.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Headers;

public class MovieDetailsActivity extends YouTubeBaseActivity {
    private static final String NOW_PLAYING_URL =
            "https://api.themoviedb.org/3/movie/%s/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private static final String YouTube_API_KEY = "AIzaSyDLrIjLMK2CKiavp9l_AZKKLi9miWzhMSQ";
    private final String TAG = "MovieDetailsActivity";
    private final float HIGH_RATING = 7;
    YouTubePlayerView youTubePlayerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieDetailsBinding movieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        Intent i = getIntent();
        Movie movie = (Movie) getIntent().getParcelableExtra("movie");
        movieBinding.setMovie(movie);
        youTubePlayerView = movieBinding.movieDetailPreview;
        float rating = 0;
        if(movie.getRating() != null)
            rating = movie.getRating();
        getYouTubeVideoKey(movie.getId(), rating);
    }

    private void getYouTubeVideoKey(String id, final float rating){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = String.format(NOW_PLAYING_URL, id);
        client.get(url, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON response) {
                        Log.d(TAG, "onSuccess");
                        JSONObject jsonObject = response.jsonObject;
                        try {
                            JSONArray results = jsonObject.getJSONArray("results");
                            final String key = results.getJSONObject(0).getString("key");
                            youTubePlayerView.initialize(YouTube_API_KEY, new YouTubePlayer.OnInitializedListener() {
                                @Override
                                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                                    if(rating < HIGH_RATING)
                                        youTubePlayer.cueVideo(key);
                                    else
                                        youTubePlayer.loadVideo(key);
                                }

                                @Override
                                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                                }
                            });
                            Log.i(TAG, "Key " + key);

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
