package com.codepath.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String id;
    private String title;
    private String overview;
    private String posterPath;
    private String backdropPath;
    private String rating;
    private Double popularity;
    private String releaseDate;

    public Movie(JSONObject jsonObject) throws JSONException {
        id = jsonObject.getString("id");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        backdropPath = jsonObject.getString("backdrop_path");
        rating = jsonObject.getString("vote_average");
        popularity = jsonObject.getDouble("popularity");
        releaseDate = jsonObject.getString("release_date");
    }
    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for(int i=0; i<movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getRating() { return rating; }

    public Double getPopularity() {
        return popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        // TODO: Make a call to the Image API to get the size
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropPath() {
        // TODO: Make a call to the Image API to get the size
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }
}
