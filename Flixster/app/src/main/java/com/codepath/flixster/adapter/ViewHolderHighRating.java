package com.codepath.flixster.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.codepath.flixster.MovieDetailsActivity;
import com.codepath.flixster.R;
import com.codepath.flixster.models.GlideApp;
import com.codepath.flixster.models.Movie;

public class ViewHolderHighRating extends RecyclerView.ViewHolder {
    private View view;
    private ImageView movie_backdropImage;
    private TextView movie_title;
    private TextView movie_overview;
    private Context context;

    public ViewHolderHighRating(View movieView, Context context){
        super(movieView);
        this.context = context;
        view = movieView;
        movie_backdropImage = (ImageView) movieView.findViewById(R.id.backdropImage);
        movie_title = (TextView) movieView.findViewById(R.id.title);
        movie_overview = (TextView) movieView.findViewById(R.id.overview);
    }

    public void bind(final Movie movie){
        GlideApp.with(context).load(R.drawable.place_holder).into(movie_backdropImage);
        GlideApp.with(context).load(movie.getPosterPath()).into(movie_backdropImage);
        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            movie_title.setText(movie.getTitle());
            movie_overview.setText(movie.getOverview());
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // first parameter is the context, second is the class of the activity to launch
                Intent i = new Intent(context, MovieDetailsActivity.class);
                // put "extras" into the bundle for access in the second activity
                i.putExtra("id", movie.getId());
                i.putExtra("title", movie.getTitle());
                i.putExtra("rating", movie.getRating());
                i.putExtra("popularity", movie.getPopularity());
                i.putExtra("releaseDate", movie.getReleaseDate());
                i.putExtra("overview", movie.getOverview());
                // brings up the second activity
                context.startActivity(i);
            }
        });
    }
}
